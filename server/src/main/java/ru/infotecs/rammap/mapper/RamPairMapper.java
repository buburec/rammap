package ru.infotecs.rammap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.infotecs.rammap.dto.RamPairDTO;
import ru.infotecs.rammap.entity.RamPair;

/**
 * Mapper interface for converting between {@link RamPair} entity and {@link RamPairDTO} DTO.
 * <p>
 * This interface uses MapStruct to provide the mapping methods for converting
 * between the entity and DTO representations of a RAM pair.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface RamPairMapper {

    /**
     * Singleton instance of the mapper.
     * <p>
     * MapStruct provides a default implementation of the mapper using the factory method.
     * </p>
     */
    RamPairMapper INSTANCE = Mappers.getMapper(RamPairMapper.class);

    /**
     * Converts a {@link RamPair} entity to a {@link RamPairDTO} DTO.
     * <p>
     * This method maps the properties of the {@link RamPair} entity to the corresponding
     * properties in the {@link RamPairDTO} DTO. The {@code ramObject} property of the entity
     * is mapped to the {@code ramObjectDTO} property of the DTO.
     * </p>
     *
     * @param ramPair the {@link RamPair} entity to convert
     * @return the corresponding {@link RamPairDTO} DTO
     */
    @Mapping(source = "ramObject", target = "ramObjectDTO")
    RamPairDTO toDTO(RamPair ramPair);

    /**
     * Converts a {@link RamPairDTO} DTO to a {@link RamPair} entity.
     * <p>
     * This method maps the properties of the {@link RamPairDTO} DTO to the corresponding
     * properties in the {@link RamPair} entity. The {@code ramObjectDTO} property of the DTO
     * is mapped to the {@code ramObject} property of the entity.
     * </p>
     *
     * @param ramPairDTO the {@link RamPairDTO} DTO to convert
     * @return the corresponding {@link RamPair} entity
     */
    @Mapping(source = "ramObjectDTO", target = "ramObject")
    RamPair toEntity(RamPairDTO ramPairDTO);
}
