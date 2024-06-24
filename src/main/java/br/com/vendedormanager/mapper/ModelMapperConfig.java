package br.com.vendedormanager.mapper;

import br.com.vendedormanager.dto.VendedorRequestDTO;
import br.com.vendedormanager.model.Vendedor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ModelMapperConfig {

    public static ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Ignora o campo 'id' ao mapear de VendedorRequestDTO para Vendedor
        modelMapper.addMappings(new PropertyMap<VendedorRequestDTO, Vendedor>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        return modelMapper;
    }
}
