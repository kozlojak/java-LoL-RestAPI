package pl.jakubkozlowski.learning.firststeps.converter;


import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface ModelMapperConverter<DTO, Entity> {

    default DTO convertEntity(Entity entity) {
        if (entity == null) {
            return null;
        }
        return getModelMapper().map(entity, getDTOClass());
    }

    default DTO convertEntity(Entity entity, List<Function<DTO, DTO>> additionalConverters) {
        DTO dto = convertEntity(entity);

        if (dto == null || CollectionUtils.isEmpty(additionalConverters)) {
            return dto;
        }
        return additionalConverters.stream().reduce(Function.identity(), Function::andThen).apply(dto);
    }

    default Entity convertDTO(DTO dto) {
        return dto == null ? null : getModelMapper().map(dto, getEntityClass());
    }

    Class<Entity> getEntityClass();

    Class<DTO> getDTOClass();

    ModelMapper getModelMapper();


    default List<DTO> convertListEntity(List<Entity> entityList) {
        if (CollectionUtils.isEmpty(entityList))
            return Collections.emptyList();
        else
            return entityList.stream().map(this::convertEntity).collect(Collectors.toList());
    }

    default List<Entity> convertListDTO(List<DTO> dTOList) {
        if (CollectionUtils.isEmpty(dTOList))
            return Collections.emptyList();
        else
            return dTOList.stream().map(this::convertDTO).collect(Collectors.toList());
    }

}