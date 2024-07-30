package ru.infotecs.driver.dto;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RamPairDTO {
    private String key;
    private RamObjectDTO ramObjectDTO;

    public static final RamPairDTO NULL_OBJECT = new RamPairDTO() {
        @Override
        public String toString() {
            return "$classname{" +
                    "key='" + null + '\'' +
                    ", ramObjectDTO=" + null +
                    '}';
        }
    };
}
