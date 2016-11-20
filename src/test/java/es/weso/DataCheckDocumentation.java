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
public class DataCheckDocumentation {

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
    public void DataCheckTurtleExample() throws Exception {
        String strData = String.join("\n",
         "prefix : <http://example.org/>",
         ":x :p :y"
        );
        String strFormat = "TURTLE";
        String strResult = "N-TRIPLES";
        Option<String> base = Option.apply(null);
        String expected = "<http://example.org/x> <http://example.org/p> <http://example.org/y> .\n";
        this.mockMvc.perform(get(API_URI + "/data/check")
                .param("data", strData)
                .param("dataFormat",strFormat)
                .param("resultFormat",strResult))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.result").value(expected)).
                andDo(document("dataCheckTurtleExample", requestParameters(
                        parameterWithName("data").description("RDF Data string"),
                        parameterWithName("dataFormat").description("Name of data format"),
                        parameterWithName("resultFormat").description("Name of Result format")
                )));
    }

}
