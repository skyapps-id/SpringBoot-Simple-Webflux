package com.kltestbe.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import net.datafaker.Faker;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    Faker faker = new Faker();
    
    String username = faker.name().username();
    String email = faker.name().username()+"@gmail.com";
    String password = faker.random().toString();

    @Test
    public void stage1_signUp() throws Exception {
        this.mockMvc.perform(
            post("/users/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"email\": \""+email+"\", \"username\":  \""+username+"\",  \"password\":  \""+password+"\"}")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void stage2_login() throws Exception {
        this.mockMvc.perform(
            post("/users/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{\"username\":\"ajii\",\"password\":\"halo\"}")
            .accept(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void stage3_login_faild() throws Exception {
        this.mockMvc.perform(
            post("/users/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{\"username\":\"Asdeasd\",\"password\":\"ASDASD\"}")
            .accept(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isUnauthorized());
    }
}
