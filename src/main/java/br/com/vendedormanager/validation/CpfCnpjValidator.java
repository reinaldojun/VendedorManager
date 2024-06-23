package br.com.vendedormanager.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

    @Override
    public void initialize(CpfCnpj constraintAnnotation) {
        // Método opcional
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Tratamento para campos opcionais
        }

        // Implementação da lógica de validação de CPF ou CNPJ
        return isCpfValid(value) || isCnpjValid(value);
    }

    private boolean isCpfValid(String cpf) {
        // Formato esperado de CPF: 123.456.789-09
        cpf = cpf.replaceAll("[^\\d]", ""); // Remove caracteres não numéricos

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11)
            return false;

        // Verifica se todos os dígitos são iguais, o que torna o CPF inválido
        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        // Calcula os dígitos verificadores
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = soma % 11;
        if (primeiroDigito > 1) {
            primeiroDigito = 11 - primeiroDigito;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = soma % 11;
        if (segundoDigito > 1) {
            segundoDigito = 11 - segundoDigito;
        }

        // Verifica se os dígitos verificadores calculados conferem com os informados
        return Character.getNumericValue(cpf.charAt(9)) == primeiroDigito &&
                Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
    }

    private boolean isCnpjValid(String cnpj) {
        // Formato esperado de CNPJ: 12.345.678/0001-90
        cnpj = cnpj.replaceAll("[^\\d]", ""); // Remove caracteres não numéricos

        // Verifica se o CNPJ possui 14 dígitos
        if (cnpj.length() != 14)
            return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        int primeiroDigito = soma % 11;
        if (primeiroDigito < 2) {
            primeiroDigito = 0;
        } else {
            primeiroDigito = 11 - primeiroDigito;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        int segundoDigito = soma % 11;
        if (segundoDigito < 2) {
            segundoDigito = 0;
        } else {
            segundoDigito = 11 - segundoDigito;
        }

        // Verifica se os dígitos verificadores calculados conferem com os informados
        return Character.getNumericValue(cnpj.charAt(12)) == primeiroDigito &&
                Character.getNumericValue(cnpj.charAt(13)) == segundoDigito;
    }
}
