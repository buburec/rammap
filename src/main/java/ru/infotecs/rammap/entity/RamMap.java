package ru.infotecs.rammap.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
@Component
public class RamMap {
    private Map<String, RamObject> ramObjectMap = new ConcurrentHashMap<>();

    public RamObject get(String key) {
        return this.ramObjectMap.get(key);
    }

    public boolean put(RamPair ramPair) {
        if (RamMap.isTtlExpired(ramPair.getRamObject().getTimeToLive())) {
            ramPair.getRamObject().setTimeToLive(RamObject.getDefaultTtl());
        }
        try {
            this.ramObjectMap.put(ramPair.getKey(), ramPair.getRamObject());
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public RamObject remove(String key) {
        return this.ramObjectMap.remove(key);
    }

    public void expire() {
        if (!this.ramObjectMap.keySet().isEmpty()) {
            for (String key : this.ramObjectMap.keySet()) {
                RamObject ramObject = this.ramObjectMap.get(key);
                int timeToLive = ramObject.getTimeToLive() - 1;
                if (RamMap.isTtlExpired(timeToLive)) {
                    this.ramObjectMap.remove(key);
                } else {
                    ramObject.setTimeToLive(timeToLive);
                }
            }
        }
    }

    private static boolean isTtlExpired(int timeToLive) {
        return timeToLive == 0;
    }

}
