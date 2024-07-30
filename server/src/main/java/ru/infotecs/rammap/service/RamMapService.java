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

/**
 * Service for managing RAM map operations such as setting, getting, removing, dumping, and loading data.
 */
@Service
@RequiredArgsConstructor
public class RamMapService {
    private static final String SCHEDULED_EXPIRATION = "*/1 * * * * *"; // Run every second
    private static final String DUMPED_FILENAME = "dumped-";
    private static final String LOADED_FILENAME = "loaded-";
    private static final String SUFFIX = ".json";

    private final RamMap ramMap;

    /**
     * Sets a key-value pair in the RAM map.
     *
     * @param ramPairDTO the data transfer object containing the key and value to be set
     * @return true if the operation was successful, false otherwise
     */
    public boolean set(RamPairDTO ramPairDTO) {
        RamPair ramPair = RamPairMapper.INSTANCE.toEntity(ramPairDTO);
        return this.ramMap.put(ramPair);
    }

    /**
     * Retrieves a value from the RAM map by key.
     *
     * @param key the key of the value to be retrieved
     * @return the corresponding RamObjectDTO, or null if not found
     */
    public RamObjectDTO get(String key) {
        return RamObjectMapper.INSTANCE.toDTO(this.ramMap.get(key));
    }

    /**
     * Removes a key-value pair from the RAM map.
     *
     * @param key the key of the value to be removed
     * @return the corresponding RamObjectDTO that was removed, or null if not found
     */
    public RamObjectDTO remove(String key) {
        RamObject ramObject = this.ramMap.remove(key);
        return RamObjectMapper.INSTANCE.toDTO(ramObject);
    }

    /**
     * Dumps the current RAM map to a temporary file in JSON format.
     *
     * @return a File containing the dumped data, or null if an error occurs
     */
    public File dump() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = File.createTempFile(DUMPED_FILENAME, SUFFIX);
            objectMapper.writeValue(file, this.ramMap.getRamObjectMap());
            return file;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Loads data from a MultipartFile into the RAM map.
     *
     * @param file the file containing the data to be loaded
     * @return the loaded RamMapDTO, or null if an error occurs
     */
    public RamMapDTO load(MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File transferredFile = File.createTempFile(LOADED_FILENAME, SUFFIX);
            file.transferTo(transferredFile);
            Map<String, RamObject> ramObjectMap = objectMapper.readValue(transferredFile, new TypeReference<>() {});
            this.ramMap.setRamObjectMap(ramObjectMap);
            return RamMapMapper.INSTANCE.toDTO(this.ramMap);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Periodically expires entries in the RAM map based on their time-to-live.
     * This method is scheduled to run periodically according to the cron expression.
     */
    @Scheduled(cron = RamMapService.SCHEDULED_EXPIRATION)
    public void expire() {
        this.ramMap.expire();
    }
}
