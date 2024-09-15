package ru.infotecs.rammap.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * Represents a concurrent in-memory map for storing RAM objects with expiration handling.
 * <p>
 * This class provides methods to add, retrieve, remove, and manage RAM objects stored
 * in a concurrent map, including automatic expiration based on a time-to-live (TTL) value.
 * </p>
 */
@Getter
@Setter
@Component
public class RamMap {

    /**
     * The map that stores RAM objects with their associated keys.
     * <p>
     * This map uses a concurrent hash map to ensure thread-safe operations.
     * </p>
     */
    private Map<String, RamObject> ramObjectMap = new ConcurrentHashMap<>();

    /**
     * Retrieves the RAM object associated with the given key.
     *
     * @param key the key associated with the desired RAM object
     * @return the RAM object associated with the key, or {@code null} if no object is found
     */
    public RamObject get(String key) {
        return this.ramObjectMap.get(key);
    }

    /**
     * Adds or updates a RAM object in the map.
     * <p>
     * If the TTL (time-to-live) of the RAM object has expired, it resets the TTL to the default value.
     * </p>
     *
     * @param ramPair the RAM pair containing the key and RAM object to be added or updated
     * @return {@code true} if the operation was successful, {@code false} otherwise
     */
    public boolean put(RamPair ramPair) {
        if (RamMap.isTtlExpired(ramPair.getRamObject().getTimeToLive())) {
            ramPair.getRamObject().setTimeToLive(RamObject.DEFAULT_TTL);
        }
        try {
            this.ramObjectMap.put(ramPair.getKey(), ramPair.getRamObject());
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Removes the RAM object associated with the given key from the map.
     *
     * @param key the key of the RAM object to be removed
     * @return the removed RAM object, or {@code null} if no object was found for the given key
     */
    public RamObject remove(String key) {
        return this.ramObjectMap.remove(key);
    }

    /**
     * Expires RAM objects that have reached their TTL (time-to-live).
     * <p>
     * This method iterates through the map and removes objects with expired TTL,
     * or decrements the TTL for objects that are still valid.
     * </p>
     */
    public void expire() {
        if (!this.ramObjectMap.keySet().isEmpty()) {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.invoke(new ExpireTask(this.ramObjectMap));
        }
    }

    /**
     * Private static inner class representing the task for expiring RAM objects.
     * Extends RecursiveTask to allow parallel execution in a ForkJoinPool.
     */
    @RequiredArgsConstructor
    private static class ExpireTask extends RecursiveTask<Void> {
        private static final int THRESHOLD = 10;
        private final Map<String, RamObject> ramObjectMap;

        /**
         * Computes the task to expire RAM objects based on their TTL.
         * If the size of the map is below the threshold, it processes sequentially.
         * Otherwise, it splits the task into two subtasks for parallel processing.
         *
         * @return null (as this is a Void task).
         */
        @Override
        protected Void compute() {
            if (this.ramObjectMap.size() <= ExpireTask.THRESHOLD) {
                for (String key : this.ramObjectMap.keySet()) {
                    RamObject ramObject = this.ramObjectMap.get(key);
                    int timeToLive = ramObject.getTimeToLive() - 1;
                    if (RamMap.isTtlExpired(timeToLive)) {
                        this.ramObjectMap.remove(key);
                    } else {
                        ramObject.setTimeToLive(timeToLive);
                    }
                }
            } else {
                int middle = this.ramObjectMap.size() / 2;
                Map<String, RamObject> firstHalf = this.ramObjectMap
                        .entrySet()
                        .stream()
                        .limit(middle)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                Map<String, RamObject> secondHalf = this.ramObjectMap
                        .entrySet()
                        .stream()
                        .skip(middle)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                ExpireTask firstTask = new ExpireTask(firstHalf);
                ExpireTask secondTask = new ExpireTask(secondHalf);
                ExpireTask.invokeAll(firstTask, secondTask);
            }
            return null;
        }
    }

    /**
     * Checks if the given TTL value has expired.
     *
     * @param timeToLive the TTL value to check
     * @return {@code true} if the TTL has expired (i.e., is 0), {@code false} otherwise
     */
    private static boolean isTtlExpired(int timeToLive) {
        return timeToLive == 0;
    }

}
