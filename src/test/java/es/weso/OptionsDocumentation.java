package es.weso;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static es.weso.Config.API_URI;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OptionsDocumentation {

    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void availableSchemaFormats() throws Exception {
        this.mockMvc.perform(get(API_URI + "/availableSchemaFormats").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("availableSchemaFormats"));
    }

    @Test
    public void availableSchemaEngines() throws Exception {
        this.mockMvc.perform(get(API_URI + "/availableSchemaEngines").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("availableSchemaEngines"));
    }

    @Test
    public void availableDataFormats() throws Exception {
        this.mockMvc.perform(get(API_URI + "/availableDataFormats").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("availableDataFormats"));
    }

}
