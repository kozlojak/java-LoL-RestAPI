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
import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;
import pl.jakubkozlowski.learning.firststeps.service.ItemOrderService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemOrderTestConstants.*;


@RunWith(SpringRunner.class)
@WebMvcTest(ItemOrderController.class)
public class ItemOrderControllerTest {

    @MockBean
    private ItemOrderService itemOrderService;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;
    private ItemOrderDTO itemOrderDTOSet1;
    private ItemOrderDTO itemOrderDTOSet2;
    private List<ItemOrderDTO> itemOrderDTOList;

    @Before
    public void setUp() throws Exception {
        itemOrderDTOSet1 = new ItemOrderDTO(ID_1, ITEM_1, ORDER_1);
        itemOrderDTOSet2 = new ItemOrderDTO(ID_2, ITEM_2, ORDER_2);
        itemOrderDTOList = Arrays.asList(itemOrderDTOSet1, itemOrderDTOSet2);
        objectMapper = new ObjectMapper();

        Mockito.when(itemOrderService.findById(ID_1)).thenReturn(itemOrderDTOSet1);
        Mockito.when(itemOrderService.findAll()).thenReturn(itemOrderDTOList);
    }

    @Test
    public void whenSave_thenReturnItemOrderDTO() throws Exception {
        String content = mvc.perform(post(ITEM_ORDER_BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemOrderDTOSet1)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ItemOrderDTO actual = objectMapper.readValue(content, new TypeReference<ItemOrderDTO>() {
        });
        assertThat(actual).isEqualTo(itemOrderDTOSet1);
    }

    @Test
    public void whenFindAll_thenReturntJsonArrayOfItemOrders() throws Exception {
        String content = mvc.perform(get(ITEM_ORDER_BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<ItemOrderDTO> actual = objectMapper.readValue(content, new TypeReference<List<ItemOrderDTO>>() {
        });
        assertThat(actual).isEqualTo(itemOrderDTOList);
    }

    @Test
    public void whenFindById_thenReturntJsonArrayOfItemOrderWithGivenId() throws Exception {
        String content = mvc.perform(get(ITEM_ORDER_BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        ItemOrderDTO actual = objectMapper.readValue(content, new TypeReference<ItemOrderDTO>() {
        });
        assertThat(actual).isEqualTo(itemOrderDTOSet1);
    }


    @Test
    public void whenUpdate_thenReturnJsonArrayOfUpdatedItemOrder() throws Exception {
        String content = mvc.perform(put(ITEM_ORDER_BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemOrderDTOSet1)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ItemOrderDTO actual = objectMapper.readValue(content, new TypeReference<ItemOrderDTO>() {
        });
        assertThat(actual).isEqualTo(itemOrderDTOSet1);
    }

    @Test
    public void whenDeleteById_thenReturnStatusNoContent() throws Exception {
        mvc.perform(delete(ITEM_ORDER_BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}