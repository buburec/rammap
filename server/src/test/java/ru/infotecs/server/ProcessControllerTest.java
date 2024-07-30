package ru.infotecs.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import ru.infotecs.rammap.controller.ProcessController;
import ru.infotecs.rammap.dto.RamMapDTO;
import ru.infotecs.rammap.dto.RamObjectDTO;
import ru.infotecs.rammap.dto.RamPairDTO;
import ru.infotecs.rammap.service.RamMapService;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProcessControllerTest {

    @Mock
    private RamMapService ramMapService;

    @InjectMocks
    private ProcessController processController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet_Success() {
        String key = "testKey";
        RamObjectDTO ramObjectDTO = new RamObjectDTO();
        when(ramMapService.get(key)).thenReturn(ramObjectDTO);
        ResponseEntity<RamObjectDTO> response = processController.get(key);
        assertEquals(ResponseEntity.ok(ramObjectDTO), response);
        verify(ramMapService, times(1)).get(key);
    }

    @Test
    void testGet_NotFound() {
        String key = "testKey";
        when(ramMapService.get(key)).thenReturn(null);
        ResponseEntity<RamObjectDTO> response = processController.get(key);
        assertEquals(ResponseEntity.badRequest().build(), response);
        verify(ramMapService, times(1)).get(key);
    }

    @Test
    void testSet_Success() {
        RamPairDTO ramPairDTO = new RamPairDTO();
        when(ramMapService.set(ramPairDTO)).thenReturn(true);
        ResponseEntity<Void> response = processController.set(ramPairDTO);
        assertEquals(ResponseEntity.ok().build(), response);
        verify(ramMapService, times(1)).set(ramPairDTO);
    }

    @Test
    void testSet_Failure() {
        RamPairDTO ramPairDTO = new RamPairDTO();
        when(ramMapService.set(ramPairDTO)).thenReturn(false);
        ResponseEntity<Void> response = processController.set(ramPairDTO);
        assertEquals(ResponseEntity.badRequest().build(), response);
        verify(ramMapService, times(1)).set(ramPairDTO);
    }

    @Test
    void testRemove_Success() {
        String key = "testKey";
        RamObjectDTO ramObjectDTO = new RamObjectDTO();
        when(ramMapService.remove(key)).thenReturn(ramObjectDTO);
        ResponseEntity<RamObjectDTO> response = processController.remove(key);
        assertEquals(ResponseEntity.ok(ramObjectDTO), response);
        verify(ramMapService, times(1)).remove(key);
    }

    @Test
    void testRemove_NotFound() {
        String key = "testKey";
        when(ramMapService.remove(key)).thenReturn(null);
        ResponseEntity<RamObjectDTO> response = processController.remove(key);
        assertEquals(ResponseEntity.badRequest().build(), response);
        verify(ramMapService, times(1)).remove(key);
    }

    @Test
    void testDump_Success() {
        File file = new File("test.txt");
        when(ramMapService.dump()).thenReturn(file);
        ResponseEntity<FileSystemResource> response = processController.dump();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(ramMapService, times(1)).dump();
    }

    @Test
    void testDump_Failure() {
        when(ramMapService.dump()).thenReturn(null);
        ResponseEntity<FileSystemResource> response = processController.dump();
        assertEquals(ResponseEntity.badRequest().build(), response);
        verify(ramMapService, times(1)).dump();
    }

    @Test
    void testLoad_Success() {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "test data".getBytes()
        );
        RamMapDTO ramMapDTO = new RamMapDTO();
        when(ramMapService.load(mockFile)).thenReturn(ramMapDTO);
        ResponseEntity<RamMapDTO> response = processController.load(mockFile);
        assertEquals(ResponseEntity.ok(ramMapDTO), response);
        verify(ramMapService, times(1)).load(mockFile);
    }

    @Test
    void testLoad_Failure() {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "test data".getBytes()
        );
        when(ramMapService.load(mockFile)).thenThrow(new RuntimeException());
        ResponseEntity<RamMapDTO> response = processController.load(mockFile);
        assertEquals(ResponseEntity.badRequest().build(), response);
        verify(ramMapService, times(1)).load(mockFile);
    }
}
