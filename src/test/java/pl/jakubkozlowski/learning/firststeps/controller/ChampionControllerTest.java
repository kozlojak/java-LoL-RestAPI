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

import java.util.Arrays;
import java.util.List;

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
    private List<ChampionDTO> championDTOList;

    @Before
    public void setUp() throws Exception {
        championDTOAatrox = new ChampionDTO(ID_1, AATROX);
        championDTOAhri = new ChampionDTO(ID_2, AHRI);
        championDTOList = Arrays.asList(championDTOAatrox, championDTOAhri);
        objectMapper = new ObjectMapper();

        Mockito.when(championService.findAll()).thenReturn(championDTOList);
        Mockito.when(championService.findById(ID_1)).thenReturn(championDTOAatrox);

    }

    @Test
    public void whenFindAll_thenReturnJsonArrayOfChampions() throws Exception {


        String content = mvc.perform(get(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<ChampionDTO> actualList = objectMapper.readValue(content, new TypeReference<List<ChampionDTO>>() {
        });
        assertThat(actualList).isEqualTo(championDTOList);
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
