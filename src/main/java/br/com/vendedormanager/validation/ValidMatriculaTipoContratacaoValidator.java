package br.com.vendedormanager.validation;

import br.com.vendedormanager.dto.VendedorRequestUpdateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMatriculaTipoContratacaoValidator implements ConstraintValidator<ValidMatriculaTipoContratacao, VendedorRequestUpdateDTO> {

    private static final String MATRICULA_REGEX = "^\\d{8}-(OUT|CLT|PJ)$";

    @Override
    public void initialize(ValidMatriculaTipoContratacao constraintAnnotation) {
        // Inicialização, se necessário
    }

    @Override
    public boolean isValid(VendedorRequestUpdateDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true; // ou false, dependendo da sua lógica de negócio
        }

        String matricula = dto.getMatricula();
        String tipoContratacao = dto.getTipoContratacao();

        if (matricula == null || tipoContratacao == null) {
            return false;
        }

        // Verificar se a matrícula está no formato correto
        if (!matricula.matches(MATRICULA_REGEX)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Matrícula deve seguir o formato 00000000-OUT, 00000000-CLT ou 00000000-PJ")
                    .addConstraintViolation();
            return false;
        }

        // Verificar se o sufixo da matrícula corresponde ao tipo de contratação
        String expectedSuffix = tipoContratacao.equals("CLT") ? "CLT"
                : tipoContratacao.equals("Outsourcing") ? "OUT"
                : tipoContratacao.equals("Pessoa Jurídica") ? "PJ"
                : "";

        if (!matricula.endsWith(expectedSuffix)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Sufixo da matrícula não corresponde ao tipo de contratação")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
