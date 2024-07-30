package ru.infotecs.rammap.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing a key-value pair in RAM.
 * <p>
 * This class is used to encapsulate a key and its associated RAM object, providing
 * a way to transfer or store a mapping between a key and its corresponding value.
 * </p>
 */
@Getter
@Setter
public class RamPairDTO {

    /**
     * The key associated with the RAM object.
     * <p>
     * This string represents the unique identifier or the reference for the RAM object
     * stored in the system.
     * </p>
     */
    private String key;

    /**
     * The RAM object associated with the key.
     * <p>
     * This is the value or data object that is linked to the given key. It contains
     * information such as the value and time-to-live (TTL) of the RAM object.
     * </p>
     */
    private RamObjectDTO ramObjectDTO;
}
