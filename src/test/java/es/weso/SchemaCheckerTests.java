package es.weso;

import es.weso.schema.Schemas;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import scala.Option;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchemaCheckerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
        String strSchema = "<S> { <p> . }";
        String strFormat = "SHEXC";
        String strSchemaName = "SHEX";
        String strResult = "SHEXJ";
        Option<String> base = Option.apply(null);
        String expected =
                Schemas.fromString(strSchema,strFormat,strSchemaName,base).get().serialize(strResult).get();
        this.mockMvc.perform(get("/schema/check")
                .param("schema", strSchema)
                .param("schemaFormat","SHEXC")
                .param("schemaName","SHEX")
                .param("resultFormat","SHEXJ"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.result").value(expected));
    }

}
