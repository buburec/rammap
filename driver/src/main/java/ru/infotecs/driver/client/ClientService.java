package ru.infotecs.driver.client;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.infotecs.driver.dto.RamMapDTO;
import ru.infotecs.driver.dto.RamObjectDTO;
import ru.infotecs.driver.dto.RamPairDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ClientService {
    private final RestTemplate restTemplate = new RestTemplate();

    public RamObjectDTO getValue(String url, String key) {
        try {
            return this.restTemplate.getForObject(url + key, RamObjectDTO.class);
        } catch (HttpClientErrorException.BadRequest errorException) {
            return RamObjectDTO.NULL_OBJECT;
        }
    }

    public void setValue(String url, RamPairDTO ramPairDTO) {
        try {
            this.restTemplate.postForObject(url, ramPairDTO, Void.class);
        }  catch (HttpClientErrorException.BadRequest errorException) {
            throw new RuntimeException();
        }
    }

    public void deleteValue(String url, String key) {
        try {
            this.restTemplate.delete(url + key);
        } catch (HttpClientErrorException.BadRequest errorException) {
            throw new RuntimeException();
        }
    }

    public File dumpData(String url) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/octet-stream");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
            byte[] data = response.getBody();
            File targetFile = new File("dumped.json");
            try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
                outputStream.write(data);
            }
            return targetFile;
        } catch (HttpClientErrorException | IOException errorException) {
            throw new RuntimeException("Failed to download dump or writing file data", errorException);
        }
    }

    public RamMapDTO loadData(String url, File file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", getFileAsResource(file));
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<RamMapDTO> response = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity,RamMapDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException | IOException errorException) {
            throw new RuntimeException("Failed to upload file", errorException);
        }
    }

    private Resource getFileAsResource(File file) throws IOException {
        return new ByteArrayResource(new FileInputStream(file).readAllBytes()) {
            @Override
            public String getFilename() {
                return file.getName();
            }
        };
    }

}
