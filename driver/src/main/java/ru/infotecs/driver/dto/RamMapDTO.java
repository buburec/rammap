package ru.infotecs.driver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter @Setter
@ToString
public class RamMapDTO {
    private Map<String, RamObjectDTO> ramObjectMap;

    public static final RamMapDTO NULL_OBJECT = new RamMapDTO() {
        @Override
        public String toString() {
            return "$classname{" +
                    "ramObjectMap=" + null +
                    '}';
        }
    };
}
