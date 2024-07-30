package ru.infotecs.driver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Data Transfer Object (DTO) for holding a map of RAM objects.
 * This class represents a collection of {@link RamObjectDTO} instances
 * indexed by their respective keys.
 * <p>
 * The class uses Lombok to automatically generate getters, setters, and a
 * {@link #toString()} method for convenience.
 * </p>
 */
@Getter
@Setter
@ToString
public class RamMapDTO {
    /**
     * A map where the key is a {@link String} representing the identifier of
     * a RAM object, and the value is an instance of {@link RamObjectDTO}.
     */
    private Map<String, RamObjectDTO> ramObjectMap;

    /**
     * A static final instance of {@link RamMapDTO} representing a null or
     * empty RAM map. This can be used as a placeholder or default value when
     * an actual instance is not available.
     * <p>
     * The {@link #toString()} method is overridden to provide a string
     * representation of this null object.
     * </p>
     */
    public static final RamMapDTO NULL_OBJECT = new RamMapDTO() {
        @Override
        public String toString() {
            return "$classname{" +
                    "ramObjectMap=" + null +
                    '}';
        }
    };
}
