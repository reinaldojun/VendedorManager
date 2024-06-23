package br.com.vendedormanager.converter;

import br.com.vendedormanager.dto.VendedorRequestDTO;
import br.com.vendedormanager.mapper.BaseConverter;
import br.com.vendedormanager.model.Vendedor;
import org.springframework.stereotype.Component;

@Component
public class VendedorRequestConverter implements BaseConverter<Vendedor, VendedorRequestDTO> {
}
