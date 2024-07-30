package ru.infotecs.rammap.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.infotecs.rammap.dto.RamMapDTO;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.dto.RamPairDTO;
import ru.infotecs.rammap.entity.RamMap;
import ru.infotecs.rammap.entity.RamObject;
import ru.infotecs.rammap.entity.RamPair;
import ru.infotecs.rammap.mapper.RamMapMapper;
import ru.infotecs.rammap.mapper.RamObjectMapper;
import ru.infotecs.rammap.mapper.RamPairMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RamMapService {
    private static final String SCHEDULED_EXPIRATION = "*/1 * * * * *";
    private static final String DUMPED_FILENAME = "dumped-";
    private static final String LOADED_FILENAME = "loaded-";
    private static final String SUFFIX = ".json";
    private final RamMap ramMap;

    public boolean set(RamPairDTO ramPairDTO) {
        RamPair ramPair = RamPairMapper.INSTANCE.toEntity(ramPairDTO);
        return this.ramMap.put(ramPair);
    }

    public RamObjectDTO get(String key) {
        return RamObjectMapper.INSTANCE.toDTO(this.ramMap.get(key));
    }

    public RamObjectDTO remove(String key) {
        RamObject ramObject = this.ramMap.remove(key);
        return RamObjectMapper.INSTANCE.toDTO(ramObject);
    }

    public File dump() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = File.createTempFile(RamMapService.DUMPED_FILENAME, RamMapService.SUFFIX);
            objectMapper.writeValue(file, this.ramMap.getRamObjectMap());
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RamMapDTO load(MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File transferredFile = File.createTempFile(RamMapService.LOADED_FILENAME, RamMapService.SUFFIX);
            file.transferTo(transferredFile);
            Map<String, RamObject> ramObjectMap = objectMapper.readValue(transferredFile, new TypeReference<>() {});
            this.ramMap.setRamObjectMap(ramObjectMap);
            return RamMapMapper.INSTANCE.toDTO(this.ramMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Scheduled(cron = RamMapService.SCHEDULED_EXPIRATION)
    public void expire() {
        this.ramMap.expire();
    }
}
