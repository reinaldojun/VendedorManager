package br.com.vendedormanager.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface BaseConverter<Entity, Dto> {

	//@Autowired
	ModelMapper modelMapper = new ModelMapper();

	default Class<Dto> getDtoClass() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericInterfaces()[0];
		return (Class<Dto>) type.getActualTypeArguments()[1];
	}

	default Class<Entity> getEntityClass() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericInterfaces()[0];
		return (Class<Entity>) type.getActualTypeArguments()[0];
	}

	default Dto toDto(Entity entity) {
		return modelMapper.map(entity, getDtoClass());
	}

	default List<Dto> toListDto(List<Entity> listEntity) {
		return modelMapper.map(listEntity, new TypeToken<List<Dto>>() {}.getType());
	}

	default Entity toEntity(Dto dto) {
		return modelMapper.map(dto, getEntityClass());
	}

	default List<Entity> toListEntity(List<Dto> listDto) {
		return modelMapper.map(listDto, new TypeToken<List<Entity>>() {}.getType());
	}

	default <FROM, TO> TO toType(FROM from, Class<TO> type) {
		return modelMapper.map(from, type);
	}
}
