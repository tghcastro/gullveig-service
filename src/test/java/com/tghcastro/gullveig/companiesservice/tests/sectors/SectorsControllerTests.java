package com.tghcastro.gullveig.companiesservice.tests.sectors;

import com.tghcastro.gullveig.companiesservice.repositories.SectorsRepository;
import com.tghcastro.gullveig.companiesservice.controllers.SectorsController;
import com.tghcastro.gullveig.companiesservice.services.SectorsServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {SectorsServiceImpl.class, SectorsController.class})
public class SectorsControllerTests {
    @MockBean
    private SectorsRepository repository;

    @Autowired
    SectorsController controller;

    @Autowired
    private MockMvc mockMvc;

    private String uri = "/api/v1/sectors";

    @Before
    public void before() {

    }

    @Test
    public void whenCreatingValidSector_ShouldReturnCreated() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String sectorName = UUID.randomUUID().toString().substring(0,20);

        String sector = "{" +
                "    \"name\": \"" + sectorName + "\",\n" +
                "    \"enabled\": true\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(sector)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    //TODO: How I can test this scenario as UnitTest
    @Test
    public void whenCreatingSectorWithNameHavingLessThan5Chars_ShouldReturnBadRequest() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String sectorName = "1234";

        String sector = "{" +
                "    \"name\": \"" + sectorName + "\",\n" +
                "    \"enabled\": true\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(sector)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    //TODO: How I can test this scenario as UnitTest
    @Test
    public void whenCreatingSectorWithNameHavingMoreThan30Chars_ShouldReturnBadRequest() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String sectorName = "1234567890123456789012345678901";

        String sector = "{" +
                "    \"name\": \"" + sectorName + "\",\n" +
                "    \"enabled\": true\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(sector)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
