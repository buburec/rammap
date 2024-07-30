package ru.infotecs.rammap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.infotecs.rammap.dto.RamMapDTO;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.dto.RamPairDTO;
import ru.infotecs.rammap.service.RamMapService;

import java.io.File;

/**
 * The {@code ProcessController} class handles HTTP requests related to RAM data processing.
 * This controller provides endpoints for CRUD operations on RAM data:
 * <ul>
 *     <li>Retrieving data by key</li>
 *     <li>Setting data by key</li>
 *     <li>Deleting data by key</li>
 *     <li>Dumping all data to a file</li>
 *     <li>Loading data from a file</li>
 * </ul>
 *
 * @see RamMapService
 * @see RamMapDTO
 * @see RamObjectDTO
 * @see RamPairDTO
 */
@RestController
@RequestMapping("process/")
@RequiredArgsConstructor
public class ProcessController {

    private final RamMapService ramMapService;

    /**
     * Retrieves the {@link RamObjectDTO} associated with the specified key.
     * <p>
     * Returns a {@link ResponseEntity} containing the {@link RamObjectDTO} if found,
     * otherwise returns a {@link ResponseEntity} with a 400 Bad Request status.
     * </p>
     *
     * @param key the key associated with the data to retrieve
     * @return a {@link ResponseEntity} containing the {@link RamObjectDTO} or a bad request response
     */
    @GetMapping("get/{key}")
    public ResponseEntity<RamObjectDTO> get(@PathVariable String key) {
        RamObjectDTO ramObjectDTO = this.ramMapService.get(key);
        if (ProcessController.isPresent(ramObjectDTO)) {
            return ResponseEntity.ok(ramObjectDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Sets the specified {@link RamPairDTO} data.
     * <p>
     * Returns a {@link ResponseEntity} with a 200 OK status if the data was successfully set,
     * otherwise returns a {@link ResponseEntity} with a 400 Bad Request status.
     * </p>
     *
     * @param ramPairDTO the data to set
     * @return a {@link ResponseEntity} indicating the result of the operation
     */
    @PostMapping("set")
    public ResponseEntity<Void> set(@RequestBody RamPairDTO ramPairDTO) {
        if (this.ramMapService.set(ramPairDTO)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Deletes the {@link RamObjectDTO} associated with the specified key.
     * <p>
     * Returns a {@link ResponseEntity} containing the deleted {@link RamObjectDTO} if found,
     * otherwise returns a {@link ResponseEntity} with a 400 Bad Request status.
     * </p>
     *
     * @param key the key associated with the data to delete
     * @return a {@link ResponseEntity} containing the deleted {@link RamObjectDTO} or a bad request response
     */
    @DeleteMapping("delete/{key}")
    public ResponseEntity<RamObjectDTO> remove(@PathVariable String key) {
        RamObjectDTO ramObjectDTO = this.ramMapService.remove(key);
        if (ProcessController.isPresent(ramObjectDTO)) {
            return ResponseEntity.ok(ramObjectDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Dumps all data to a file and returns it as a {@link ResponseEntity}.
     * <p>
     * Returns a {@link ResponseEntity} containing the file as a {@link FileSystemResource}
     * with a 200 OK status if the file is successfully created and exists,
     * otherwise returns a {@link ResponseEntity} with a 400 Bad Request status.
     * </p>
     *
     * @return a {@link ResponseEntity} containing the file or a bad request response
     */
    @GetMapping("dump")
    public ResponseEntity<FileSystemResource> dump() {
        File file = ramMapService.dump();
        if (ProcessController.isPresent(file) && file.exists()) {
            FileSystemResource resource = new FileSystemResource(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Loads data from the provided file.
     * <p>
     * Returns a {@link ResponseEntity} containing the {@link RamMapDTO} if the data was successfully loaded,
     * otherwise returns a {@link ResponseEntity} with a 400 Bad Request status.
     * </p>
     *
     * @param file the file containing the data to load
     * @return a {@link ResponseEntity} containing the {@link RamMapDTO} or a bad request response
     */
    @PostMapping(value = "/load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RamMapDTO> load(@RequestParam("file") MultipartFile file) {
        RamMapDTO ramMapDTO = this.ramMapService.load(file);
        if (ProcessController.isPresent(ramMapDTO)) {
            return ResponseEntity.ok(ramMapDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Checks if the provided object is not null.
     *
     * @param object the object to check
     * @return {@code true} if the object is not null, {@code false} otherwise
     */
    private static boolean isPresent(Object object) {
        return object != null;
    }

}
