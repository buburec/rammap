package ru.infotecs.rammap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.infotecs.rammap.dto.RamPairDTO;
import ru.infotecs.rammap.entity.RamPair;

@Mapper(componentModel = "spring")
public interface RamPairMapper {
    RamPairMapper INSTANCE = Mappers.getMapper(RamPairMapper.class);

    @Mapping(source = "ramObject", target = "ramObjectDTO")
    RamPairDTO toDTO(RamPair ramPair);
    @Mapping(source = "ramObjectDTO", target = "ramObject")
    RamPair toEntity(RamPairDTO ramPairDTO);
}
