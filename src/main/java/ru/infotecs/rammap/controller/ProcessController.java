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

@RestController
@RequestMapping("process/")
@RequiredArgsConstructor
public class ProcessController {
    private final RamMapService ramMapService;

    @GetMapping("get/{key}")
    public ResponseEntity<RamObjectDTO> get(@PathVariable String key) {
        RamObjectDTO ramObjectDTO = this.ramMapService.get(key);
        if (ProcessController.isPresent(ramObjectDTO)) {
            return ResponseEntity.ok(ramObjectDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("set")
    public ResponseEntity<Void> set(@RequestBody RamPairDTO ramPairDTO) {
        if (this.ramMapService.set(ramPairDTO)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("delete/{key}")
    public ResponseEntity<RamObjectDTO> remove(@PathVariable String key) {
        RamObjectDTO ramObjectDTO = this.ramMapService.remove(key);
        if (ProcessController.isPresent(ramObjectDTO)) {
            return ResponseEntity.ok(ramObjectDTO);
        }
        return ResponseEntity.badRequest().build();
    }

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

    @PostMapping(value = "/load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RamMapDTO> load(@RequestParam("file") MultipartFile file) {
        RamMapDTO ramMapDTO = this.ramMapService.load(file);
        if (ProcessController.isPresent(ramMapDTO)) {
            return ResponseEntity.ok(ramMapDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    private static boolean isPresent(Object object) {
        return object != null;
    }

}
