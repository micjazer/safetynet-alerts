package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.PhoneAlertService;
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

@WebMvcTest(PhoneAlertController.class)
public class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneAlertService phoneAlertService;

    @Test
    public void testGetPhones() throws Exception {
        when(phoneAlertService.getPhonesByStation(3)).thenReturn(List.of("841-874-6512"));

        mockMvc.perform(get("/phoneAlert?firestation=3"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("841-874-6512")));
    }
}

