package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExampleRoute() throws Exception {
        mockMvc.perform(post("/logistics/optimize")
                            .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "transfers": [
                            {"weight": 5, "cost": 10},
                            {"weight": 10, "cost": 20},
                            {"weight": 3, "cost": 5},
                            {"weight": 8, "cost": 15}
                          ],
                          "maxWeight": 15
                        }""")).andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(30))
                .andExpect(jsonPath("$.transfers.length()").value(2));
    }

    @Test
    void testNoRoute() throws Exception {
        mockMvc.perform(post("/logistics/optimize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "transfers": [
                                    {"weight": 5, "cost": 10},
                                    {"weight": 8, "cost": 15}
                                  ],
                                  "maxWeight": 4
                                }""")).andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(0))
                .andExpect(jsonPath("$.transfers.length()").value(0));
    }


}
