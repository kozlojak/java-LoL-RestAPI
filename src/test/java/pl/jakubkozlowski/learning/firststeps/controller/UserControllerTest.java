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
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.jakubkozlowski.learning.firststeps.descriptor.UserTestDescriptor.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    public UserService userService;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;

    private UserDTO userDTOMark;
    private UserDTO userDTOMarkWithSelectedFields;

    @Before
    public void setUp() throws Exception {
        userDTOMark = new UserDTO(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
        userDTOMarkWithSelectedFields = new UserDTO(MARK, MARK_EMAIL, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
        objectMapper = new ObjectMapper();

        Mockito.when(userService.findUserById(ID_1)).thenReturn(userDTOMark);
        Mockito.when(userService.findUserByName(userDTOMark.getUsername())).thenReturn(userDTOMarkWithSelectedFields);
    }

    @Test
    public void whenSaveUser_thenReturnUserDTO() throws Exception {
        String content = mvc.perform(post(BASE_PATH)
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
        String content = mvc.perform(get(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        UserDTO actual = objectMapper.readValue(content, new com.fasterxml.jackson.core.type.TypeReference<UserDTO>() {
        });
        assertThat(actual).isEqualTo(userDTOMark);
    }

    @Test
    public void whenFindUserByName_thenReturnJsonArrayOfUserWithGivenName() throws Exception {
        String content = mvc.perform(get(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", userDTOMarkWithSelectedFields.getUsername()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        UserDTO actual = objectMapper.readValue(content, new com.fasterxml.jackson.core.type.TypeReference<UserDTO>() {
        });
        assertThat(actual).isEqualTo(userDTOMarkWithSelectedFields);
    }

    @Test
    public void whenUpdateEmail_thenReturnJsonArrayOfUpdatedUser() throws Exception {
        String content = mvc.perform(put(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTOMark)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        UserDTO actual = objectMapper.readValue(content, new com.fasterxml.jackson.core.type.TypeReference<UserDTO>() {
        });
        assertThat(actual).isEqualTo(userDTOMark);
    }

    @Test
    public void whenDeleteById_thenReturnStatusNoContent() throws Exception {
        mvc.perform(delete(BASE_PATH + BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}