package es.weso;

import static es.weso.utils.ScalaConverters.asJava;
import java.util.List;

import es.weso.schema.DataFormats;
import es.weso.schema.Schemas;

public class Schema2Java {

	public static List<String> availableSchemaEngines() {
        return asJava(Schemas.availableSchemaNames());
    }
	
	public static List<String> availableDataFormats() {
        return asJava(DataFormats.formatNames());
    }
	
	public static List<String> availableSchemaFormats() {
        return asJava(Schemas.availableFormats());
    }

}
