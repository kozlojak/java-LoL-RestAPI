package pl.jakubkozlowski.learning.firststeps.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.service.ChampionService;
import pl.jakubkozlowski.learning.firststeps.shared.Page;
import pl.jakubkozlowski.learning.firststeps.shared.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ChampionTestConstants.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ChampionController.class)
public class ChampionControllerTest {

    @MockBean
    private ChampionService championService;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;
    private ChampionDTO championDTOAatrox;
    private ChampionDTO championDTOAhri;
    private Pageable pageable;
    private Page<ChampionDTO> page;

    @Before
    public void setUp() throws Exception {
        championDTOAatrox = new ChampionDTO(ID_1, AATROX);
        championDTOAhri = new ChampionDTO(ID_2, AHRI);
        objectMapper = new ObjectMapper();
        pageable = new Pageable(PAGE_0, SIZE_2);
        page = new Page<>(Arrays.asList(championDTOAatrox, championDTOAhri), PAGE_0, TOTAL_COUNT_2);


        Mockito.when(championService.findPage(pageable)).thenReturn(Optional.of(page));
        Mockito.when(championService.findById(ID_1)).thenReturn(Optional.of(championDTOAatrox));

    }

    @Test
    public void whenFindPage_thenReturnJsonPageOfChampions() throws Exception {

        String content = mvc.perform(get(BASE_PATH)
                .param(PARAM_PAGE, VALUE_0)
                .param(PARAM_SIZE, VALUE_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Page<ChampionDTO> actualList = objectMapper.readValue(content, new TypeReference<Page<ChampionDTO>>() {
        });
        assertThat(actualList).isEqualTo(page);
    }

    @Test
    public void whenFindOne_thenReturnJsonArrayOfChampionWithGivenID() throws Exception {

        String content = mvc.perform(get(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        ChampionDTO actual = objectMapper.readValue(content, new TypeReference<ChampionDTO>() {
        });
        assertThat(actual).isEqualTo(championDTOAatrox);
    }

    @Test
    public void whenPersist_thenReturnJsonArrayOfPersistChampion() throws Exception {

        String content = mvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(championDTOAatrox)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ChampionDTO actual = objectMapper.readValue(content, new TypeReference<ChampionDTO>() {
        });
        assertThat(actual).isEqualTo(championDTOAatrox);

    }

    @Test
    public void whenUpdate_thenReturnJsonArrayOfPersistChampion() throws Exception {
        String content = mvc.perform(put(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(championDTOAatrox)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ChampionDTO actual = objectMapper.readValue(content, new TypeReference<ChampionDTO>() {
        });
        assertThat(actual).isEqualTo(championDTOAatrox);
    }

    @Test
    public void whenDelete_thenReturnStatusNoContent() throws Exception {
        mvc.perform(delete(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
