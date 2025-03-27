package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testAddPerson() throws Exception {
        Person p = new Person("Jane", "Smith", "Address", "City", "Zip", "123", "email");
        doNothing().when(personService).addPerson(Mockito.any(Person.class));
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person p = new Person("John", "Doe", "Address", "City", "Zip", "123", "email");
        when(personService.updatePerson(Mockito.any(Person.class))).thenReturn(true);
        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        when(personService.deletePerson("John", "Doe")).thenReturn(true);
        mockMvc.perform(delete("/person?firstName=John&lastName=Doe"))
                .andExpect(status().isOk());
    }
}
