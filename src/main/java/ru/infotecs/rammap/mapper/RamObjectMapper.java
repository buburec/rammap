package ru.infotecs.rammap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.entity.RamObject;

@Mapper(componentModel = "spring")
public interface RamObjectMapper {
    RamObjectMapper INSTANCE = Mappers.getMapper(RamObjectMapper.class);

    RamObjectDTO toDTO(RamObject ramObject);
    RamObject toEntity(RamObjectDTO ramObjectDTO);
}
