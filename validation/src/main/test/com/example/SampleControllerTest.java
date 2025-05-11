package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testPayment() throws Exception {
        String json = "{ \"host\": \"web\", \"data\": { \"orderNo\": \"12345\", \"amount\": \"100\", \"authFlag\": \"1\" } }";

        mockMvc.perform(post("/api/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testPayment_hostInvalid() throws Exception {
        String json = "{ \"host\": \"any\", \"data\": { \"orderNo\": \"12345\", \"amount\": \"100\", \"authFlag\": \"1\" } }";

        mockMvc.perform(post("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPayment_orderNoInvalid() throws Exception {
        String json = "{ \"host\": \"any\", \"data\": { \"orderNo\": \"\", \"amount\": \"100\", \"authFlag\": \"1\" } }";

        mockMvc.perform(post("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPayment_amountInvalid() throws Exception {
        String json = "{ \"host\": \"any\", \"data\": { \"orderNo\": \"12345\", \"amount\": \"-1\", \"authFlag\": \"1\" } }";

        mockMvc.perform(post("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPayment_authFlagInvalid() throws Exception {
        String json = "{ \"host\": \"any\", \"data\": { \"orderNo\": \"12345\", \"amount\": \"100\", \"authFlag\": \"AA\" } }";

        mockMvc.perform(post("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}