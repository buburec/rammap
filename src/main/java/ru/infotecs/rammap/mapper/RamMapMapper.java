package ru.infotecs.rammap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.infotecs.rammap.dto.RamMapDTO;
import ru.infotecs.rammap.entity.RamMap;

@Mapper(componentModel = "spring")
public interface RamMapMapper {
    RamMapMapper INSTANCE = Mappers.getMapper(RamMapMapper.class);

    RamMapDTO toDTO(RamMap ramMap);
    RamMap toEntity(RamMapDTO ramMapDTO);
}
