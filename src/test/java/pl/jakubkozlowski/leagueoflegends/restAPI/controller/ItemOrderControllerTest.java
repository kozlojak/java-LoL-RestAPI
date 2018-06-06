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
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ItemOrderTestConstants;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemOrderDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.service.ItemOrderService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        itemOrderDTOSet1 = new ItemOrderDTO(ItemOrderTestConstants.ID_1, ItemOrderTestConstants.ITEM_1, ItemOrderTestConstants.ORDER_1);
        itemOrderDTOSet2 = new ItemOrderDTO(ItemOrderTestConstants.ID_2, ItemOrderTestConstants.ITEM_2, ItemOrderTestConstants.ORDER_2);
        itemOrderDTOList = Arrays.asList(itemOrderDTOSet1, itemOrderDTOSet2);
        objectMapper = new ObjectMapper();

        Mockito.when(itemOrderService.findById(ItemOrderTestConstants.ID_1)).thenReturn(Optional.of(itemOrderDTOSet1));
        Mockito.when(itemOrderService.findAll()).thenReturn(Optional.of(itemOrderDTOList));
    }

    @Test
    public void whenSave_thenReturnItemOrderDTO() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.post(ItemOrderTestConstants.ITEM_ORDER_BASE_PATH)
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
        String content = mvc.perform(MockMvcRequestBuilders.get(ItemOrderTestConstants.ITEM_ORDER_BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<ItemOrderDTO> actual = objectMapper.readValue(content, new TypeReference<List<ItemOrderDTO>>() {
        });
        assertThat(actual).isEqualTo(itemOrderDTOList);
    }

    @Test
    public void whenFindById_thenReturntJsonArrayOfItemOrderWithGivenId() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get(ItemOrderTestConstants.ITEM_ORDER_BASE_PATH + ItemOrderTestConstants.BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        ItemOrderDTO actual = objectMapper.readValue(content, new TypeReference<ItemOrderDTO>() {
        });
        assertThat(actual).isEqualTo(itemOrderDTOSet1);
    }


    @Test
    public void whenUpdate_thenReturnJsonArrayOfUpdatedItemOrder() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.put(ItemOrderTestConstants.ITEM_ORDER_BASE_PATH + ItemOrderTestConstants.BY_ID)
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
        mvc.perform(MockMvcRequestBuilders.delete(ItemOrderTestConstants.ITEM_ORDER_BASE_PATH + ItemOrderTestConstants.BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}