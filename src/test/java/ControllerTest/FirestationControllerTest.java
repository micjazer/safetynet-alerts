package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FirestationResponseDTO;
import com.safetynet.alerts.service.FirestationService;
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

@WebMvcTest(FirestationController.class)
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationService;

    @Test
    public void testGetFirestation() throws Exception {
        when(firestationService.getPersonsByStation(3)).thenReturn(new FirestationResponseDTO(List.of(), 1, 0));
        mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk());
    }
}

