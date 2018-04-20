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
import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.service.ItemPageService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemPageTestConstans.*;

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
        itemPageDTOAttack = new ItemPageDTO(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID);
        itemPageDTODefence = new ItemPageDTO(ID_2, DEFENCE, DEFENCE_DSC, DEFENCE_CHAMPION_ID, DEFENCE_ROLE_ID, DEFENCE_USER_ID);
        itemPageDTOList = Arrays.asList(itemPageDTOAttack, itemPageDTODefence);
        objectMapper = new ObjectMapper();

        Mockito.when(itemPageService.findItemPageById(ID_1)).thenReturn(itemPageDTOAttack);
        Mockito.when(itemPageService.findItemPageByPagename(ATTACK)).thenReturn(itemPageDTOAttack);
        Mockito.when(itemPageService.findAllItemPages()).thenReturn(itemPageDTOList);
    }

    @Test
    public void whenSaveItemPage_thenReturnItemPageDTO() throws Exception {
        String content = mvc.perform(post(BASE_PATH)
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
        String content = mvc.perform(get(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        List<ItemPageDTO> actual = objectMapper.readValue(content, new TypeReference<List<ItemPageDTO>>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOList);
    }

    @Test
    public void whenFindItemPageById_thenReturntJsonArrayOfItemPageWithGivenId() throws Exception {
        String content = mvc.perform(get(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        ItemPageDTO actual = objectMapper.readValue(content, new TypeReference<ItemPageDTO>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenFindItemPageByName_thenReturnJsonArrayOfItemPageWithGivenName() throws Exception {
        String content = mvc.perform(get(BASE_PATH + PAGENAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        ItemPageDTO actual = objectMapper.readValue(content, new TypeReference<ItemPageDTO>() {
        });
        assertThat(actual).isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenUpdateItemPage_thenReturnJsonArrayOfUpdatedItemPage() throws Exception {
        String content = mvc.perform(put(BASE_PATH + BY_ID)
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
        mvc.perform(delete(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}