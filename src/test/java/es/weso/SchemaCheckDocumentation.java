package es.weso;
import es.weso.schema.Schemas;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import scala.Option;

import static es.weso.Config.API_URI;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchemaCheckDocumentation {

    @Autowired
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
    public void schemaCheckShExExample() throws Exception {
        String strSchema = "<S> { <p> . }";
        String strFormat = "SHEXC";
        String strSchemaName = "SHEX";
        String strResult = "SHEXJ";
        Option<String> base = Option.apply(null);
        String expected =
                Schemas.fromString(strSchema,strFormat,strSchemaName,base).get().serialize(strResult).get();
        this.mockMvc.perform(get(API_URI + "/schema/check")
                .param("schema", strSchema)
                .param("schemaFormat","SHEXC")
                .param("schemaEngine","SHEX")
                .param("resultFormat","SHEXJ"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.result").value(expected)).
                andDo(document("schemaCheckShExExample", requestParameters(
                        parameterWithName("schema").description("Schema string"),
                        parameterWithName("schemaFormat").description("Name of schema format"),
                        parameterWithName("schemaEngine").description("Name of schema engine"),
                        parameterWithName("resultFormat").description("Result format")
                )));
    }

    @Test
    public void postSchemaCheckShExExample() throws Exception {
        String strSchema = "<S> { <p> . }";
        String strFormat = "SHEXC";
        String strSchemaName = "SHEX";
        String strResult = "SHEXJ";
        Option<String> base = Option.apply(null);
        String expected =
                Schemas.fromString(strSchema,strFormat,strSchemaName,base).get().serialize(strResult).get();
        this.mockMvc.perform(post(API_URI + "/schema/check")
                .param("schema", strSchema)
                .param("schemaFormat","SHEXC")
                .param("schemaEngine","SHEX")
                .param("resultFormat","SHEXJ"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.result").value(expected)).
                andDo(document("postSchemaCheckShExExample"));
    }
}
