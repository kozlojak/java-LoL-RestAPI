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
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.UserTestConstants;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;
    private UserDTO userDTOMark;
    private UserDTO userDTOMarkWithSelectedFields;

    @Before
    public void setUp() throws Exception {
        userDTOMark = new UserDTO(UserTestConstants.ID_1, UserTestConstants.MARK, UserTestConstants.MARK_EMAIL, UserTestConstants.MARK_PASSWORD, UserTestConstants.MARK_FAV_ROLE_ID, UserTestConstants.MARK_FAV_CHAMP_ID);
        userDTOMarkWithSelectedFields = new UserDTO(UserTestConstants.MARK, UserTestConstants.MARK_EMAIL, UserTestConstants.MARK_FAV_ROLE_ID, UserTestConstants.MARK_FAV_CHAMP_ID);
        objectMapper = new ObjectMapper();

        Mockito.when(userService.findById(UserTestConstants.ID_1)).thenReturn(Optional.of(userDTOMark));
        Mockito.when(userService.findByUsername(userDTOMark.getUsername())).thenReturn(Optional.of(userDTOMarkWithSelectedFields));
    }

    @Test
    public void whenSaveUser_thenReturnUserDTO() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.post(UserTestConstants.BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTOMark)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        UserDTO actual = objectMapper.readValue(content, new TypeReference<UserDTO>() {
        });
        assertThat(actual).isEqualTo(userDTOMark);
    }

    @Test
    public void whenFindUserById_thenReturntJsonArrayOfUserWithGivenId() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get(UserTestConstants.BASE_PATH + UserTestConstants.BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        UserDTO actual = objectMapper.readValue(content, new TypeReference<UserDTO>() {
        });
        assertThat(actual).isEqualTo(userDTOMark);
    }

    @Test
    public void whenFindUserByName_thenReturnJsonArrayOfUserWithGivenName() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get(UserTestConstants.BASE_PATH + UserTestConstants.USERNAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        UserDTO actual = objectMapper.readValue(content, new TypeReference<UserDTO>() {
        });
        assertThat(actual).isEqualTo(userDTOMarkWithSelectedFields);
    }

    @Test
    public void whenUpdateUser_thenReturnJsonArrayOfUpdatedUser() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.put(UserTestConstants.BASE_PATH + UserTestConstants.BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTOMark)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        UserDTO actual = objectMapper.readValue(content, new TypeReference<UserDTO>() {
        });
        assertThat(actual).isEqualTo(userDTOMark);
    }

    @Test
    public void whenDeleteById_thenReturnStatusNoContent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(UserTestConstants.BASE_PATH + UserTestConstants.BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}