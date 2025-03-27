package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationCrudService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FirestationController.class)
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationCrudService firestationService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testAddFirestation() throws Exception {
        Firestation fs = new Firestation("29 15th St", "2");
        doNothing().when(firestationService).addFirestation(Mockito.any(Firestation.class));

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(fs)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFirestation() throws Exception {
        Firestation fs = new Firestation("1509 Culver St", "4");
        when(firestationService.updateFirestation(Mockito.any(Firestation.class))).thenReturn(true);

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(fs)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        when(firestationService.deleteFirestation("1509 Culver St")).thenReturn(true);

        mockMvc.perform(delete("/firestation?address=1509 Culver St"))
                .andExpect(status().isOk());
    }
}


