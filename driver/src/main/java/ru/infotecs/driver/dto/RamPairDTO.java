package ru.infotecs.driver.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing a key-value pair in the RAM.
 * This class encapsulates a key associated with a RAM object and the
 * RAM object itself.
 * <p>
 * The class uses Lombok annotations to automatically generate getters,
 * setters, and a `toString` method. It also provides constructors with and
 * without arguments.
 * </p>
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RamPairDTO {
    /**
     * The key associated with the RAM object. This is used to identify
     * the object in the RAM storage.
     */
    private String key;

    /**
     * The RAM object associated with the key. This object contains the
     * data and metadata related to the key.
     */
    private RamObjectDTO ramObjectDTO;

    /**
     * A static final instance of {@link RamPairDTO} representing a null
     * or empty key-value pair. This can be used as a placeholder or default
     * value when an actual instance is not available.
     * <p>
     * The {@link #toString()} method is overridden to provide a string
     * representation of this null object.
     * </p>
     */
    public static final RamPairDTO NULL_OBJECT = new RamPairDTO() {
        @Override
        public String toString() {
            return "$classname{" +
                    "key='" + null + '\'' +
                    ", ramObjectDTO=" + null +
                    '}';
        }
    };
}
