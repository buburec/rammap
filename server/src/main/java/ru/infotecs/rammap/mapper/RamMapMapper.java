package ru.infotecs.rammap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.infotecs.rammap.dto.RamMapDTO;
import ru.infotecs.rammap.entity.RamMap;

/**
 * Mapper interface for converting between {@link RamMap} entity and {@link RamMapDTO} DTO.
 * <p>
 * This interface uses MapStruct to provide the mapping methods for converting
 * between the entity and DTO representations of the RAM map.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface RamMapMapper {

    /**
     * Singleton instance of the mapper.
     * <p>
     * MapStruct provides a default implementation of the mapper using the factory method.
     * </p>
     */
    RamMapMapper INSTANCE = Mappers.getMapper(RamMapMapper.class);

    /**
     * Converts a {@link RamMap} entity to a {@link RamMapDTO} DTO.
     * <p>
     * This method maps the properties of the {@link RamMap} entity to the corresponding
     * properties in the {@link RamMapDTO} DTO.
     * </p>
     *
     * @param ramMap the {@link RamMap} entity to convert
     * @return the corresponding {@link RamMapDTO} DTO
     */
    RamMapDTO toDTO(RamMap ramMap);

    /**
     * Converts a {@link RamMapDTO} DTO to a {@link RamMap} entity.
     * <p>
     * This method maps the properties of the {@link RamMapDTO} DTO to the corresponding
     * properties in the {@link RamMap} entity.
     * </p>
     *
     * @param ramMapDTO the {@link RamMapDTO} DTO to convert
     * @return the corresponding {@link RamMap} entity
     */
    RamMap toEntity(RamMapDTO ramMapDTO);
}
