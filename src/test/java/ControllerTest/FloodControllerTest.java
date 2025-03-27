package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.dto.FloodResponseDTO;
import com.safetynet.alerts.service.FloodService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FloodController.class)
public class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FloodService floodService;

    @Test
    public void testGetFloodStations() throws Exception {
        Map<String, List<FloodResponseDTO>> map = new HashMap<>();
        map.put("29 15th St", List.of(new FloodResponseDTO("Jane", "Doe", "123-456", 13, List.of("med1"), List.of("all1"))));
        when(floodService.getFloodInfo(List.of(2))).thenReturn(map);

        mockMvc.perform(get("/flood/stations?stations=2"))
                .andExpect(status().isOk());
    }
}

