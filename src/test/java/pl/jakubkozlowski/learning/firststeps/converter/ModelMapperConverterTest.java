package pl.jakubkozlowski.learning.firststeps.converter;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ModelMapperConverterTest<DTO, Entity> {

    public abstract DTO prepareDTO();

    public abstract Entity prepareEntity();

    public abstract List<DTO> prepareListDTO();

    public abstract List<Entity> prepareListEntity();

    public abstract ModelMapperConverter<DTO, Entity> getConverter();

    public abstract List<Function<DTO, DTO>> getFunctions();

    public abstract DTO getConvertedAfterAdditionalFunctions();

    @Test
    public void whenConvertEntity_thenReturnDto() {
        assertThat(getConverter().convertEntity(prepareEntity())).isEqualTo(prepareDTO());
    }

    @Test
    public void whenConvertWithAdditionalConverters_thenReturnDto() {
        DTO dto = getConverter().convertEntity(prepareEntity(), getFunctions());
        assertThat(dto).isEqualTo(getConvertedAfterAdditionalFunctions());
    }

    @Test
    public void whenConvertDTO_thenReturnEntity() {
        assertThat(getConverter().convertDTO(prepareDTO())).isEqualTo(prepareEntity());
    }

    @Test
    public void whenConvertEnityWithNullArgument_thenReturnNull() {
        assertThat(getConverter().convertEntity(null)).isEqualTo(null);
    }

    @Test
    public void whenConvertDTOyWithNullArgument_thenReturnNull() {
        assertThat(getConverter().convertDTO(null)).isEqualTo(null);
    }

    @Test
    public void whenConvertDTOList_theReturnEntityList() {
        assertThat(getConverter().convertListDTO(prepareListDTO())).isEqualTo(prepareListEntity());
    }

    @Test
    public void whenConvertEntityList_theReturnEntityList() {
        assertThat(getConverter().convertListEntity(prepareListEntity())).isEqualTo(prepareListDTO());
    }

    @Test
    public void whenConvertDTOListWithEmptyList_thenReturnEmptyList() {
        assertThat(getConverter().convertListDTO(Collections.emptyList())).isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenConvertEntityListWithEmptyList_thenReturnEmptyList() {
        assertThat(getConverter().convertListEntity(Collections.emptyList())).isEqualTo(Collections.emptyList());
    }
}