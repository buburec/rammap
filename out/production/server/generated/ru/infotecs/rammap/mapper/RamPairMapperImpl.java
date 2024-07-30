package ru.infotecs.rammap.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.dto.RamPairDTO;
import ru.infotecs.rammap.entity.RamObject;
import ru.infotecs.rammap.entity.RamPair;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-30T14:38:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class RamPairMapperImpl implements RamPairMapper {

    @Override
    public RamPairDTO toDTO(RamPair ramPair) {
        if ( ramPair == null ) {
            return null;
        }

        RamPairDTO ramPairDTO = new RamPairDTO();

        ramPairDTO.setRamObjectDTO( ramObjectToRamObjectDTO( ramPair.getRamObject() ) );
        ramPairDTO.setKey( ramPair.getKey() );

        return ramPairDTO;
    }

    @Override
    public RamPair toEntity(RamPairDTO ramPairDTO) {
        if ( ramPairDTO == null ) {
            return null;
        }

        RamPair ramPair = new RamPair();

        ramPair.setRamObject( ramObjectDTOToRamObject( ramPairDTO.getRamObjectDTO() ) );
        ramPair.setKey( ramPairDTO.getKey() );

        return ramPair;
    }

    protected RamObjectDTO ramObjectToRamObjectDTO(RamObject ramObject) {
        if ( ramObject == null ) {
            return null;
        }

        RamObjectDTO ramObjectDTO = new RamObjectDTO();

        ramObjectDTO.setValue( ramObject.getValue() );
        ramObjectDTO.setTimeToLive( ramObject.getTimeToLive() );

        return ramObjectDTO;
    }

    protected RamObject ramObjectDTOToRamObject(RamObjectDTO ramObjectDTO) {
        if ( ramObjectDTO == null ) {
            return null;
        }

        RamObject ramObject = new RamObject();

        ramObject.setValue( ramObjectDTO.getValue() );
        ramObject.setTimeToLive( ramObjectDTO.getTimeToLive() );

        return ramObject;
    }
}
