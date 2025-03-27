package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testAddMedicalRecord() throws Exception {
        MedicalRecord mr = new MedicalRecord("Jane", "Doe", "02/02/1990", List.of(), List.of());
        doNothing().when(medicalRecordService).addMedicalRecord(Mockito.any(MedicalRecord.class));

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mr)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        MedicalRecord mr = new MedicalRecord("John", "Doe", "01/01/1980", List.of(), List.of());
        when(medicalRecordService.updateMedicalRecord(Mockito.any(MedicalRecord.class))).thenReturn(true);

        mockMvc.perform(put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mr)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        when(medicalRecordService.deleteMedicalRecord("John", "Doe")).thenReturn(true);

        mockMvc.perform(delete("/medicalRecord?firstName=John&lastName=Doe"))
                .andExpect(status().isOk());
    }
}

