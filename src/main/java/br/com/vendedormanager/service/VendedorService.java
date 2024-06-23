package br.com.vendedormanager.service;

import br.com.vendedormanager.converter.VendedorRequestConverter;
import br.com.vendedormanager.converter.VendedorResponseConverter;
import br.com.vendedormanager.dto.VendedorRequestDTO;
import br.com.vendedormanager.dto.VendedorResponseDTO;
import br.com.vendedormanager.model.Vendedor;
import br.com.vendedormanager.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendedorService {
    private final VendedorRepository vendedorRepository;
    private final VendedorRequestConverter vendedorRequestConverter;
    private final VendedorResponseConverter vendedorResponseConverter;

    public VendedorResponseDTO createVendedor(VendedorRequestDTO vendedorDTO) {
        Vendedor vendedor = vendedorRequestConverter.toEntity(vendedorDTO);
        vendedor.setMatricula(generateMatricula(vendedor.getTipoContratacao()));
        Vendedor savedVendedor = vendedorRepository.save(vendedor);
        return vendedorResponseConverter.toDto(savedVendedor);
    }

    public VendedorResponseDTO getVendedor(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedor.map(vendedorResponseConverter::toDto).orElse(null);
    }

    public VendedorResponseDTO updateVendedor(Long id, VendedorRequestDTO vendedorDTO) {
        if (!vendedorRepository.existsById(id)) {
            return null;
        }
        Vendedor vendedor = new Vendedor();
        vendedor.setId(id);
        vendedor = vendedorRequestConverter.toEntity(vendedorDTO);

        Vendedor updatedVendedor = vendedorRepository.save(vendedor);
        return vendedorResponseConverter.toDto(updatedVendedor);
    }

    public void deleteVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }

    private String generateMatricula(String tipoContratacao) {
        // Implementação da geração de matrícula seguindo as regras de negócio
        long count = vendedorRepository.count();
        String suffix;
        switch (tipoContratacao) {
            case "Outsourcing":
                suffix = "-OUT";
                break;
            case "CLT":
                suffix = "-CLT";
                break;
            case "Pessoa Jurídica":
                suffix = "-PJ";
                break;
            default:
                throw new IllegalArgumentException("Tipo de contratação inválido");
        }
        return String.format("%08d%s", count + 1, suffix);
    }
}
