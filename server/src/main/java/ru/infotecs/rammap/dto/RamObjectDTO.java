package ru.infotecs.rammap.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing an individual RAM object.
 * <p>
 * This class encapsulates the details of a RAM object, including its value and time-to-live duration.
 * </p>
 */
@Getter
@Setter
public class RamObjectDTO {

    /**
     * The value stored in the RAM object.
     * <p>
     * This can be any type of data represented as a string, which is stored and retrieved
     * in the RAM object.
     * </p>
     */
    private String value;

    /**
     * The time-to-live (TTL) for the RAM object, in seconds.
     * <p>
     * This indicates the duration for which the RAM object remains valid. After this time,
     * the object may expire or be removed from the storage.
     * </p>
     */
    private int timeToLive;
}
