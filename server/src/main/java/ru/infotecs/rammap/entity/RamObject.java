package ru.infotecs.rammap.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RamObject {
    private String value;
    private int timeToLive;
    public static final int DEFAULT_TTL = 45;
}
