package ru.infotecs.rammap.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an object stored in the RAM with a value and a time-to-live (TTL) property.
 * <p>
 * The RAM object contains a value and a TTL which determines how long the object remains valid in the map.
 * The TTL is decremented periodically and objects are expired when their TTL reaches zero.
 * </p>
 */
@Getter
@Setter
public class RamObject {

    /**
     * The value stored in the RAM object.
     * <p>
     * This could be any data associated with the object that needs to be stored.
     * </p>
     */
    private String value;

    /**
     * The time-to-live (TTL) for the RAM object, in seconds.
     * <p>
     * This determines how long the object remains valid before it is considered expired.
     * The TTL is decremented periodically, and the object is removed from the map when the TTL reaches zero.
     * </p>
     */
    private int timeToLive;

    /**
     * The default time-to-live (TTL) for a RAM object, in seconds.
     * <p>
     * This is the default TTL value assigned to an object when it is first created or when its TTL expires.
     * </p>
     */
    public static final int DEFAULT_TTL = 45;
}
