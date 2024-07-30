package ru.infotecs.rammap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RamObjectDTO {
    private String value;
    private int timeToLive;
}
