package pl.jakubkozlowski.learning.firststeps.converter;


import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;
import pl.jakubkozlowski.learning.firststeps.shared.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface ModelMapperConverter<DTO, Entity> {


    default DTO convertEntity(Entity entity) {
        return entity == null ? null : getModelMapper().map(entity, getDTOClass());
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

    default Page<DTO> convertPageEntity(Page<Entity> entityPage) {
        if (entityPage == Page.empty()) {
            return Page.empty();
        } else
            return new Page<>(convertListEntity(entityPage.getContent()), entityPage.getPage(), entityPage.getTotalCount());
    }
}