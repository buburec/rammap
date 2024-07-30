package ru.infotecs.rammap.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.entity.RamObject;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-30T14:38:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class RamObjectMapperImpl implements RamObjectMapper {

    @Override
    public RamObjectDTO toDTO(RamObject ramObject) {
        if ( ramObject == null ) {
            return null;
        }

        RamObjectDTO ramObjectDTO = new RamObjectDTO();

        ramObjectDTO.setValue( ramObject.getValue() );
        ramObjectDTO.setTimeToLive( ramObject.getTimeToLive() );

        return ramObjectDTO;
    }

    @Override
    public RamObject toEntity(RamObjectDTO ramObjectDTO) {
        if ( ramObjectDTO == null ) {
            return null;
        }

        RamObject ramObject = new RamObject();

        ramObject.setValue( ramObjectDTO.getValue() );
        ramObject.setTimeToLive( ramObjectDTO.getTimeToLive() );

        return ramObject;
    }
}
