package br.com.vendedormanager.controller.rest;

import br.com.vendedormanager.dto.VendedorRequestDTO;
import br.com.vendedormanager.dto.VendedorRequestUpdateDTO;
import br.com.vendedormanager.dto.VendedorResponseDTO;
import br.com.vendedormanager.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendedores")
@RequiredArgsConstructor
@Validated
public class VendedorController {

    private final VendedorService vendedorService;

    @Operation(summary = "Cria um novo vendedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vendedor criado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendedorRequestDTO.class),
                            examples = @ExampleObject(value = """
                                {
                                    "nome": "Reinaldo Viana",
                                    "dataNascimento": "1980-01-01",
                                    "documento": "12345678901",
                                    "email": "reinaldo.viana@example.com",
                                    "tipoContratacao": "Outsourcing",
                                    "filialId": 1
                                }
                            """))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<VendedorResponseDTO> createVendedor(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do vendedor a ser criado",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendedorRequestDTO.class),
                            examples = @ExampleObject(value = """
                            {
                                "nome": "Reinaldo Viana",
                                "dataNascimento": "1980-01-01",
                                "documento": "12345678901",
                                "email": "reinaldo.viana@example.com",
                                "tipoContratacao": "Outsourcing",
                                "filialId": 1
                            }
                        """)
                    )
            )
            @Valid @RequestBody VendedorRequestDTO vendedorDTO) {
        VendedorResponseDTO createdVendedor = vendedorService.createVendedor(vendedorDTO);
        return new ResponseEntity<>(createdVendedor, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna um vendedor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vendedor encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendedorRequestDTO.class),
                            examples = @ExampleObject(value = """
                                {
                                    "nome": "Reinaldo Viana",
                                    "dataNascimento": "1980-01-01",
                                    "documento": "12345678901",
                                    "email": "reinaldo.viana@example.com",
                                    "tipoContratacao": "Outsourcing",
                                    "filialId": 1
                                }
                            """))),
            @ApiResponse(responseCode = "404", description = "Vendedor não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VendedorResponseDTO> getVendedor(@PathVariable Long id) {
        VendedorResponseDTO vendedor = vendedorService.getVendedor(id);
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um vendedor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vendedor atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendedorRequestDTO.class),
                            examples = @ExampleObject(value = """
                                {
                                    "nome": "Reinaldo Viana",
                                    "dataNascimento": "1980-01-01",
                                    "documento": "12345678901",
                                    "email": "reinaldo.viana@example.com",
                                    "tipoContratacao": "Outsourcing",
                                    "filialId": 1
                                }
                            """))),
            @ApiResponse(responseCode = "404", description = "Vendedor não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VendedorResponseDTO> updateVendedor(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do vendedor a ser atualizado",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendedorRequestDTO.class),
                            examples = @ExampleObject(value = """
                            {
                                "nome": "Reinaldo Viana",
                                "dataNascimento": "1980-01-01",
                                "documento": "12345678901",
                                "email": "reinaldo.viana@example.com",
                                "tipoContratacao": "Outsourcing",
                                "filialId": 1
                            }
                        """)
                    )
            )
            @PathVariable Long id,
            @Valid @RequestBody VendedorRequestUpdateDTO vendedorDTO) {
        VendedorResponseDTO updatedVendedor = vendedorService.updateVendedor(id, vendedorDTO);
        return new ResponseEntity<>(updatedVendedor, HttpStatus.OK);
    }

    @Operation(summary = "Deleta um vendedor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vendedor deletado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Vendedor não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        vendedorService.deleteVendedor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
