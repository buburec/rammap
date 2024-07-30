package ru.infotecs.driver.dto;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RamObjectDTO {
    private String value;
    private int timeToLive;

    public static final RamObjectDTO NULL_OBJECT = new RamObjectDTO() {
        @Override
        public String toString() {
            return "$classname{" +
                    "value='" + null + '\'' +
                    ", timeToLive=" + null +
                    '}';
        }
    };
}
