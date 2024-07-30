package ru.infotecs.driver.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing an object in the RAM.
 * This class encapsulates the data associated with a RAM object,
 * including its value and time-to-live (TTL) duration.
 * <p>
 * The class uses Lombok annotations to automatically generate getters,
 * setters, and `toString` methods. It also provides constructors with and
 * without arguments.
 * </p>
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RamObjectDTO {
    /**
     * The value associated with the RAM object. This could represent
     * any data or content stored in the RAM.
     */
    private String value;

    /**
     * The time-to-live (TTL) duration of the RAM object in seconds.
     * This indicates how long the object should remain in the RAM before
     * it is considered expired.
     */
    private int timeToLive;

    /**
     * A static final instance of {@link RamObjectDTO} representing a null
     * or empty RAM object. This can be used as a placeholder or default
     * value when an actual instance is not available.
     * <p>
     * The {@link #toString()} method is overridden to provide a string
     * representation of this null object.
     * </p>
     */
    public static final RamObjectDTO NULL_OBJECT = new RamObjectDTO() {
        @Override
        public String toString() {
            return "$classname{" +
                    "value='" + null + '\'' +
                    ", timeToLive=" + null +
                    '}';
        }
    };
}
