package com.tghcastro.gullveig.gullveigcompaniesservice.companiessectors.controllers;

import com.tghcastro.gullveig.gullveigcompaniesservice.repositories.CompaniesSectorRepository;
import com.tghcastro.gullveig.gullveigcompaniesservice.controllers.CompaniesSectorsController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
@AutoConfigureMockMvc
public class CompaniesSectorControllerTests {
    @MockBean
    private CompaniesSectorRepository repository;

    @Autowired
    CompaniesSectorsController controller;

    @Autowired
    private MockMvc mockMvc;

    private String uri = "/api/v1/companiessectors";

    @Before
    public void before() {

    }

    @Test
    public void createSector() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String sectorName = UUID.randomUUID().toString();

        String sector = "{" +
                "    \"name\": \"" + sectorName + "\",\n" +
                "    \"enabled\": true\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(sector)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
