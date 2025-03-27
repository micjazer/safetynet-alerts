package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.CommunityEmailService;
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

@WebMvcTest(CommunityEmailController.class)
public class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityEmailService communityEmailService;

    @Test
    public void testGetEmails() throws Exception {
        when(communityEmailService.getEmailsByCity("Culver"))
                .thenReturn(List.of("john@email.com", "jane@email.com"));

        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("john@email.com")));
    }
}

