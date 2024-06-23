package br.com.vendedormanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class VendedorResponseDTO {

    private Long id;

    private String matricula;

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    private Date dataNascimento;

    @NotEmpty(message = "CPF ou CNPJ é obrigatório")
    @Pattern(regexp = "\\d{11}|\\d{14}", message = "Documento deve ser CPF ou CNPJ válido")
    private String documento;

    @NotEmpty(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve estar em um formato válido")
    private String email;

    @NotEmpty(message = "Tipo de Contratação é obrigatório")
    @Pattern(regexp = "Outsourcing|CLT|Pessoa Jurídica", message = "Tipo de Contratação deve ser Outsourcing, CLT ou Pessoa Jurídica")
    private String tipoContratacao;

    @NotNull(message = "Filial é obrigatória")
    private Long filialId;

}
