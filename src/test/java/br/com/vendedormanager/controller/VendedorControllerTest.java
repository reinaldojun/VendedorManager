package br.com.vendedormanager.controller;

import br.com.vendedormanager.controller.rest.VendedorController;
import br.com.vendedormanager.dto.VendedorRequestDTO;
import br.com.vendedormanager.dto.VendedorRequestUpdateDTO;
import br.com.vendedormanager.dto.VendedorResponseDTO;
import br.com.vendedormanager.service.VendedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class VendedorControllerTest {

    @Mock
    private VendedorService vendedorService;

    @InjectMocks
    private VendedorController vendedorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateVendedor() {
        // Mocking input DTO
        VendedorRequestDTO requestDTO = new VendedorRequestDTO();
        requestDTO.setNome("João Silva");
        // Mocking service response
        VendedorResponseDTO mockedResponse = new VendedorResponseDTO();
        mockedResponse.setId(1L);
        mockedResponse.setNome("João Silva");

        // Mocking service method
        when(vendedorService.createVendedor(any(VendedorRequestDTO.class))).thenReturn(mockedResponse);

        // Invoking controller method
        ResponseEntity<VendedorResponseDTO> responseEntity = vendedorController.createVendedor(requestDTO);

        // Verifying the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockedResponse, responseEntity.getBody());

        // Verifying service method invocation
        verify(vendedorService, times(1)).createVendedor(requestDTO);
    }

    @Test
    public void testGetVendedor() {
        // Mocking service response
        VendedorResponseDTO mockedResponse = new VendedorResponseDTO();
        mockedResponse.setId(1L);
        mockedResponse.setNome("João Silva");

        // Mocking service method
        when(vendedorService.getVendedor(anyLong())).thenReturn(mockedResponse);

        // Invoking controller method
        ResponseEntity<VendedorResponseDTO> responseEntity = vendedorController.getVendedor(1L);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedResponse, responseEntity.getBody());

        // Verifying service method invocation
        verify(vendedorService, times(1)).getVendedor(1L);
    }

    @Test
    public void testUpdateVendedor() {
        // Mocking input DTO
        VendedorRequestUpdateDTO requestDTO = new VendedorRequestUpdateDTO();
        requestDTO.setNome("João Silva");
        // Mocking service response
        VendedorResponseDTO mockedResponse = new VendedorResponseDTO();
        mockedResponse.setId(1L);
        mockedResponse.setNome("João Silva");

        // Mocking service method
        when(vendedorService.updateVendedor(anyLong(), any(VendedorRequestUpdateDTO.class))).thenReturn(mockedResponse);

        // Invoking controller method
        ResponseEntity<VendedorResponseDTO> responseEntity = vendedorController.updateVendedor(1L, requestDTO);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedResponse, responseEntity.getBody());

        // Verifying service method invocation
        verify(vendedorService, times(1)).updateVendedor(1L, requestDTO);
    }

    @Test
    public void testDeleteVendedor() {
        // Mocking service method
        doNothing().when(vendedorService).deleteVendedor(anyLong());

        // Invoking controller method
        ResponseEntity<Void> responseEntity = vendedorController.deleteVendedor(1L);

        // Verifying the response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verifying service method invocation
        verify(vendedorService, times(1)).deleteVendedor(1L);
    }
}

