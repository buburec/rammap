package ru.infotecs.rammap.dto;

import lombok.Getter;
import lombok.Setter;
import ru.infotecs.rammap.entity.RamObject;

import java.util.Map;

@Getter @Setter
public class RamMapDTO {
    private Map<String, RamObjectDTO> ramObjectMap;
}
