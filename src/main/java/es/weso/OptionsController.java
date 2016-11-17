package es.weso;

import static es.weso.Config.API_URI;
import static es.weso.ScalaConverters.asJava;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.weso.schema.DataFormats;
import es.weso.schema.Schemas;

@RestController
public class OptionsController {

    @RequestMapping(API_URI + "availableSchemaFormats")
    public List<String> availableSchemaFormats() {
        return asJava(Schemas.availableFormats());
    }

    @RequestMapping(API_URI + "availableSchemaEngines")
    public List<String> availableSchemaEngines() {
        return asJava(Schemas.availableSchemaNames());
    }

    @RequestMapping(API_URI + "availableDataFormats")
    public List<String> availableDataFormats() {
        return asJava(DataFormats.formatNames());
    }

}
