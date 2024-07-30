package ru.infotecs.rammap.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a key-value pair used in the RAM map.
 * <p>
 * A {@code RamPair} object consists of a key and an associated {@link RamObject}.
 * The key is used to identify the {@link RamObject} in the RAM map.
 * </p>
 */
@Getter
@Setter
public class RamPair {

    /**
     * The key associated with the RAM object.
     * <p>
     * This is a unique identifier used to access the associated {@link RamObject}
     * in the RAM map.
     * </p>
     */
    private String key;

    /**
     * The RAM object associated with the key.
     * <p>
     * This object contains the data and time-to-live (TTL) information.
     * It is stored in the RAM map and can be retrieved or manipulated using its key.
     * </p>
     */
    private RamObject ramObject;
}
