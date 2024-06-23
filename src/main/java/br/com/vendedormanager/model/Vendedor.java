package br.com.vendedormanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String matricula;
    private String nome;
    private Date dataNascimento;
    private String documento;
    private String email;
    private String tipoContratacao;
    private Long filialId;
}

