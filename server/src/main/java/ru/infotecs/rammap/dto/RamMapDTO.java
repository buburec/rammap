package ru.infotecs.rammap.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Data Transfer Object (DTO) for representing a map of RAM objects.
 * <p>
 * This class holds a map where each entry associates a string key with a {@link RamObjectDTO} value.
 * It is used to transfer data between different layers or systems.
 * </p>
 */
@Getter
@Setter
public class RamMapDTO {

    /**
     * A map that associates string keys with {@link RamObjectDTO} values.
     * <p>
     * The map contains all RAM objects where the key is used to uniquely identify each object.
     * </p>
     */
    private Map<String, RamObjectDTO> ramObjectMap;

}
