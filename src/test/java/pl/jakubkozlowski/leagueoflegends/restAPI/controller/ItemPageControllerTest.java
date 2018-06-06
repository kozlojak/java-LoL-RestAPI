package pl.jakubkozlowski.leagueoflegends.restAPI.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ItemPageTestConstans;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemPageDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.service.ItemPageService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemPageController.class)
public class ItemPageControllerTest {

    @MockBean
    private ItemPageService itemPageService;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;
    private ItemPageDTO itemPageDTOAttack;
    private ItemPageDTO itemPageDTODefence;
    private List<ItemPageDTO> itemPageDTOList;

    @Before
    public void setUp() throws Exception {
        itemPageDTOAttack = new ItemPageDTO(ItemPageTestConstans.ID_1, ItemPageTestConstans.ATTACK, ItemPageTestConstans.ATTACK_DSC, ItemPageTestConstans.ATTACK_CHAMPION_ID, ItemPageTestConstans.ATTACK_ROLE_ID, ItemPageTestConstans.ATTACK_USER_ID);
        itemPageDTODefence = new ItemPageDTO(ItemPageTestConstans.ID_2, ItemPageTestConstans.DEFENCE, ItemPageTestConstans.DEFENCE_DSC, ItemPageTestConstans.DEFENCE_CHAMPION_ID, ItemPageTestConstans.DEFENCE_ROLE_ID, ItemPageTestConstans.DEFENCE_USER_ID);
        itemPageDTOList = Arrays.asList(itemPageDTOAttack, itemPageDTODefence);
        objectMapper = new ObjectMapper();

        Mockito.when(itemPageService.findById(ItemPageTestConstans.ID_1)).thenReturn(Optional.of(itemPageDTOAttack));
        Mockito.when(itemPageService.findByPagename(ItemPageTestConstans.ATTACK)).thenReturn(Optional.of(itemPageDTOAttack));
        Mockito.when(itemPageService.findAll()).thenReturn(Optional.of(itemPageDTOList));
    }

    @Test
    public void whenSaveItemPage_thenReturnItemPageDTO() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.post(ItemPageTestConstans.BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemPageDTOAttack)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ItemPageDTO actual = objectMapper.readValue(content, new TypeReference<ItemPageDTO>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenFindAll_thenReturntJsonArrayOfItemPages() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get(ItemPageTestConstans.BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        List<ItemPageDTO> actual = objectMapper.readValue(content, new TypeReference<List<ItemPageDTO>>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOList);
    }

    @Test
    public void whenFindItemPageById_thenReturntJsonArrayOfItemPageWithGivenId() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get(ItemPageTestConstans.BASE_PATH + ItemPageTestConstans.BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        ItemPageDTO actual = objectMapper.readValue(content, new TypeReference<ItemPageDTO>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenFindItemPageByName_thenReturnJsonArrayOfItemPageWithGivenName() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get(ItemPageTestConstans.BASE_PATH + ItemPageTestConstans.PAGENAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        ItemPageDTO actual = objectMapper.readValue(content, new TypeReference<ItemPageDTO>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenUpdateItemPage_thenReturnJsonArrayOfUpdatedItemPage() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.put(ItemPageTestConstans.BASE_PATH + ItemPageTestConstans.BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemPageDTOAttack)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ItemPageDTO actual = objectMapper.readValue(content, new TypeReference<ItemPageDTO>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenDeleteById_thenReturnStatusNoContent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(ItemPageTestConstans.BASE_PATH + ItemPageTestConstans.BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}