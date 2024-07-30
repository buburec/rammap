package ru.infotecs.rammap.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RamObject {
    private String value;
    private int timeToLive;
    private static final int DEFAULT_TTL = 45;

    public static int getDefaultTtl() {
        return RamObject.DEFAULT_TTL;
    }
}
