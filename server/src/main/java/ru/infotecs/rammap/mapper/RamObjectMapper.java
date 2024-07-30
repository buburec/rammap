package ru.infotecs.rammap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.entity.RamObject;

/**
 * Mapper interface for converting between {@link RamObject} entity and {@link RamObjectDTO} DTO.
 * <p>
 * This interface uses MapStruct to provide the mapping methods for converting
 * between the entity and DTO representations of a RAM object.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface RamObjectMapper {

    /**
     * Singleton instance of the mapper.
     * <p>
     * MapStruct provides a default implementation of the mapper using the factory method.
     * </p>
     */
    RamObjectMapper INSTANCE = Mappers.getMapper(RamObjectMapper.class);

    /**
     * Converts a {@link RamObject} entity to a {@link RamObjectDTO} DTO.
     * <p>
     * This method maps the properties of the {@link RamObject} entity to the corresponding
     * properties in the {@link RamObjectDTO} DTO.
     * </p>
     *
     * @param ramObject the {@link RamObject} entity to convert
     * @return the corresponding {@link RamObjectDTO} DTO
     */
    RamObjectDTO toDTO(RamObject ramObject);

    /**
     * Converts a {@link RamObjectDTO} DTO to a {@link RamObject} entity.
     * <p>
     * This method maps the properties of the {@link RamObjectDTO} DTO to the corresponding
     * properties in the {@link RamObject} entity.
     * </p>
     *
     * @param ramObjectDTO the {@link RamObjectDTO} DTO to convert
     * @return the corresponding {@link RamObject} entity
     */
    RamObject toEntity(RamObjectDTO ramObjectDTO);
}
