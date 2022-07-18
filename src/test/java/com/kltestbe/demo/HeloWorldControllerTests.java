package com.kltestbe.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.kltestbe.demo.config.JwtManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class HeloWorldControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
	private JwtManager jwtManager;

    @Test
    public void stage1_isUnauthorized() throws Exception {
        this.mockMvc
        .perform(
            get("/api/helo")
        )
        .andExpect(status().isUnauthorized());
    }

    @Test
    public void stage2_isAuthorized() throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "ajii");
        claims.put("roles", "USER");
        claims.put("userId", 1);
        String subject = "ajii";
        String token = jwtManager.generateToken(claims, subject);

        this.mockMvc
            .perform(
                get("/api/helo").header("Authorization", "Bearer " + token)
            )
            .andExpect(status().isOk());
        
    }

    @Test
    public void stage3_RateLimit() throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "ajii");
        claims.put("roles", "USER");
        claims.put("userId", 1);
        String subject = "ajii";
        String token = jwtManager.generateToken(claims, subject);

        // Capacity access 30 per minutes
        for (int i=0; i<29; i++) {
            this.mockMvc
                .perform(
                    get("/api/helo").header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk());
        }
        this.mockMvc
            .perform(
                get("/api/helo").header("Authorization", "Bearer " + token)
            )
            .andExpect(status().isTooManyRequests());
    }
    
}
