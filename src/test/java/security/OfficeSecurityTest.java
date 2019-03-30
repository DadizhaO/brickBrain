package security;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import util.DtoModelsUtil;

import javax.sql.DataSource;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainMvcConfig.class})
@WebAppConfiguration
@TestPropertySource("classpath:test.properties")
public class OfficeSecurityTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        Resource initSchema = new ClassPathResource("script\\schema.sql");
        Resource data = new ClassPathResource("script\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddOfficeAuth() throws Exception {
        String json = mapper.writeValueAsString(DtoModelsUtil.officeRequest());
        mockMvc.perform(post("/office").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddOfficeNotCorrectPassword() throws Exception {
        String json = mapper.writeValueAsString(DtoModelsUtil.officeRequest());
        mockMvc.perform(post("/office")
                .with(httpBasic("test", "invalid")).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAddOfficeNotCorrectUsername() throws Exception {
        String json = mapper.writeValueAsString(DtoModelsUtil.officeRequest());
        mockMvc.perform(post("/office")
                .with(httpBasic("user", "test")).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAddOfficeNoAuth() throws Exception {
        String json = mapper.writeValueAsString(DtoModelsUtil.officeRequest());
        mockMvc.perform(post("/office").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteOfficeExistAuth() throws Exception {
        mockMvc.perform(delete("/office/{id}", "1111")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteOfficeNotExistId() throws Exception {
        mockMvc.perform(delete("/office/{id}", "-11"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Cannot delete Office by Id=-11, because it don't present")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateOfficeExist() throws Exception {
        mockMvc.perform(put("/office/{id}", "1111").param("sales", "777"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateOfficeNotExist() throws Exception {
        mockMvc.perform(put("/office/{id}", "-11").param("sales", "777"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Could not update Office id=-11, because it does not exist")));
    }

    @Test
    public void testUpdateOfficeNoAuth() throws Exception {
        mockMvc.perform(put("/office/{id}", "1111").param("sales", "777"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteOfficeExistNoAuth() throws Exception {
        mockMvc.perform(delete("/office/{id}", "1111")).andExpect(status().isUnauthorized());
    }

}
