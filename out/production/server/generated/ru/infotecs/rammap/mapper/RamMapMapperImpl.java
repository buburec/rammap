package ru.infotecs.rammap.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.infotecs.rammap.dto.RamMapDTO;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.entity.RamMap;
import ru.infotecs.rammap.entity.RamObject;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-30T18:16:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class RamMapMapperImpl implements RamMapMapper {

    @Override
    public RamMapDTO toDTO(RamMap ramMap) {
        if ( ramMap == null ) {
            return null;
        }

        RamMapDTO ramMapDTO = new RamMapDTO();

        ramMapDTO.setRamObjectMap( stringRamObjectMapToStringRamObjectDTOMap( ramMap.getRamObjectMap() ) );

        return ramMapDTO;
    }

    @Override
    public RamMap toEntity(RamMapDTO ramMapDTO) {
        if ( ramMapDTO == null ) {
            return null;
        }

        RamMap ramMap = new RamMap();

        ramMap.setRamObjectMap( stringRamObjectDTOMapToStringRamObjectMap( ramMapDTO.getRamObjectMap() ) );

        return ramMap;
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

    protected Map<String, RamObjectDTO> stringRamObjectMapToStringRamObjectDTOMap(Map<String, RamObject> map) {
        if ( map == null ) {
            return null;
        }

        Map<String, RamObjectDTO> map1 = new LinkedHashMap<String, RamObjectDTO>( Math.max( (int) ( map.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<String, RamObject> entry : map.entrySet() ) {
            String key = entry.getKey();
            RamObjectDTO value = ramObjectToRamObjectDTO( entry.getValue() );
            map1.put( key, value );
        }

        return map1;
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

    protected Map<String, RamObject> stringRamObjectDTOMapToStringRamObjectMap(Map<String, RamObjectDTO> map) {
        if ( map == null ) {
            return null;
        }

        Map<String, RamObject> map1 = new LinkedHashMap<String, RamObject>( Math.max( (int) ( map.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<String, RamObjectDTO> entry : map.entrySet() ) {
            String key = entry.getKey();
            RamObject value = ramObjectDTOToRamObject( entry.getValue() );
            map1.put( key, value );
        }

        return map1;
    }
}
