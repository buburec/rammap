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

/**
 * Service class for interacting with a remote API using {@link RestTemplate}.
 * This class provides methods for retrieving, setting, deleting data,
 * and managing file dumps and uploads.
 */
public class ClientService {
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieves a {@link RamObjectDTO} from the remote service using the provided key.
     *
     * @param url the base URL of the remote service
     * @param key the key for retrieving the value
     * @return the {@link RamObjectDTO} associated with the provided key, or {@link RamObjectDTO#NULL_OBJECT} if an error occurs
     */
    public RamObjectDTO getValue(String url, String key) {
        try {
            return this.restTemplate.getForObject(url + key, RamObjectDTO.class);
        } catch (HttpClientErrorException.BadRequest errorException) {
            return RamObjectDTO.NULL_OBJECT;
        }
    }

    /**
     * Sends a {@link RamPairDTO} to the remote service to set the value.
     *
     * @param url the URL of the remote service
     * @param ramPairDTO the {@link RamPairDTO} to be sent to the service
     * @throws RuntimeException if a {@link HttpClientErrorException.BadRequest} occurs
     */
    public void setValue(String url, RamPairDTO ramPairDTO) {
        try {
            this.restTemplate.postForObject(url, ramPairDTO, Void.class);
        } catch (HttpClientErrorException.BadRequest errorException) {
            throw new RuntimeException();
        }
    }

    /**
     * Deletes the value associated with the provided key from the remote service.
     *
     * @param url the URL of the remote service
     * @param key the key of the value to be deleted
     * @throws RuntimeException if a {@link HttpClientErrorException.BadRequest} occurs
     */
    public void deleteValue(String url, String key) {
        try {
            this.restTemplate.delete(url + key);
        } catch (HttpClientErrorException.BadRequest errorException) {
            throw new RuntimeException();
        }
    }

    /**
     * Dumps data from the remote service and saves it to a file named "dumped.json".
     *
     * @param url the URL of the remote service to retrieve the dump from
     * @return the {@link File} containing the dumped data
     * @throws RuntimeException if a {@link HttpClientErrorException} or {@link IOException} occurs
     */
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

    /**
     * Uploads a file to the remote service.
     *
     * @param url the URL of the remote service to upload the file to
     * @param file the {@link File} to be uploaded
     * @return the {@link RamMapDTO} received from the remote service in response
     * @throws RuntimeException if a {@link HttpClientErrorException} or {@link IOException} occurs
     */
    public RamMapDTO loadData(String url, File file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", getFileAsResource(file));
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<RamMapDTO> response = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, RamMapDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException | IOException errorException) {
            throw new RuntimeException("Failed to upload file", errorException);
        }
    }

    /**
     * Converts a {@link File} to a {@link Resource} for multipart file upload.
     *
     * @param file the {@link File} to be converted
     * @return the {@link Resource} representing the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    private Resource getFileAsResource(File file) throws IOException {
        return new ByteArrayResource(new FileInputStream(file).readAllBytes()) {
            @Override
            public String getFilename() {
                return file.getName();
            }
        };
    }
}
