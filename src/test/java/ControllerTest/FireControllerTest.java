package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FireResidentDTO;
import com.safetynet.alerts.dto.FireResponseDTO;
import com.safetynet.alerts.service.FireService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FireController.class)
public class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireService fireService;

    @Test
    public void testGetFireInfo() throws Exception {
        when(fireService.getFireInfo("1509 Culver St"))
                .thenReturn(new FireResponseDTO(List.of(new FireResidentDTO("John", "Boyd", "841-874-6512", 40, List.of("med1"), List.of("all1"))), "3"));

        mockMvc.perform(get("/fire?address=1509 Culver St"))
                .andExpect(status().isOk());
    }
}

