package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.service.PersonInfoService;
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

@WebMvcTest(PersonInfoController.class)
public class PersonInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonInfoService personInfoService;

    @Test
    public void testGetPersonInfo() throws Exception {
        when(personInfoService.getPersonsByLastName("Doe"))
                .thenReturn(List.of(new PersonInfoDTO("John", "Doe", "1509 Culver St", 40, "john@email.com", List.of("med1"), List.of("all1"))));

        mockMvc.perform(get("/personInfo?lastName=Doe"))
                .andExpect(status().isOk());
    }
}

