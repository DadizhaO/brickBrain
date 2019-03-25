package controller;

import config.MainMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainMvcConfig.class})
@WebAppConfiguration
@TestPropertySource("classpath:test.properties")
public class OfficeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        Resource initSchema = new ClassPathResource("script\\schema.sql");
        Resource data = new ClassPathResource("script\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetOfficeByIdExist() throws Exception {
        mockMvc.perform(get("/office/{id}", "1111"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testOfficeNotFound() throws Exception {
        mockMvc.perform(get("/office/{id}", "01"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("There is no such office")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteOfficeExist() throws Exception {
        mockMvc.perform(delete("/office/{id}", "1111"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOfficeNotExist() throws Exception {
        mockMvc.perform(delete("/office/{id}", "-11"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Cannot delete Office by Id=-11, because it don't present")));
    }

    @Test
    public void testUpdateOfficeExist() throws Exception {
        mockMvc.perform(put("/office/{id}", "1111").param("sales", "777"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOfficeNotExist() throws Exception {
        mockMvc.perform(put("/office/{id}", "-11").param("sales", "777"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Could not update Office id=-11, because it does not exist")));
    }

    @Test
    public void testGetOfficeByNameStartingWithExist() throws Exception {
        mockMvc.perform(get("/office").param("name", "ky"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOfficeByNameStartingWithNotExist() throws Exception {
        mockMvc.perform(get("/office").param("name", "kkkkkkk")).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("There is no such office, Try other name")));

    }

    @Test
    public void testGetOfficeByNameStartingWithParamNull() throws Exception {
        mockMvc.perform(get("/office")).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }

}
