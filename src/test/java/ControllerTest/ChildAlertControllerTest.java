package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.ChildWithAgeDTO;
import com.safetynet.alerts.service.ChildAlertService;
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

@WebMvcTest(ChildAlertController.class)
public class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChildAlertService childAlertService;

    @Test
    public void testGetChildren() throws Exception {
        when(childAlertService.getChildrenByAddress("29 15th St"))
                .thenReturn(new ChildAlertDTO(List.of(new ChildWithAgeDTO("Jane", "Doe", 6)), List.of("John Doe")));

        mockMvc.perform(get("/childAlert?address=29 15th St"))
                .andExpect(status().isOk());
    }
}

