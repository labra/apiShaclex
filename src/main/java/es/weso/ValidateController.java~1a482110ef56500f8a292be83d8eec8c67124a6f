package es.weso;

import es.weso.rdf.RDFReader;
import es.weso.rdf.jena.RDFAsJenaModel;
import es.weso.schema.Result;
import es.weso.schema.Schema;
import es.weso.schema.Schemas;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scala.Option;
import scala.util.Try;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ValidateController {

    @RequestMapping("/validate")
    public ValidateResult validate(
            @RequestParam(value="schema") String schema,
            @RequestParam(value="schemaFormat") String schemaFormat,
            @RequestParam(value="schemaEngine") String schemaEngine,
            @RequestParam(value="data") String data,
            @RequestParam(value="dataFormat") String dataFormat,
            @RequestParam(value="resultFormat") String resultFormat) {
        Option<String> base = Option.apply(null);
        List<String> emptyErrors = new ArrayList<String>();
        Try<Schema> t = Schemas.fromString(schema,schemaFormat, schemaEngine, base);
        if (t.isSuccess()) {
            Schema s = t.get();
            Try<String> tstr = s.serialize(resultFormat);
            if (tstr.isSuccess()) {
                Try<RDFAsJenaModel> trdf = RDFAsJenaModel.fromChars(data,dataFormat,base);
                if (trdf.isSuccess()) {
                    Result r = s.validate(trdf.get());
                    String strResult = r.serialize(resultFormat);
                    // TODO: Populate errors and solutions...
                    new ValidateResult(schema,schemaFormat,schemaEngine,data,dataFormat,emptyErrors,r.isValid(),
                            strResult, resultFormat);
                } else
                    new ValidateResult(schema,schemaFormat,schemaEngine,data,dataFormat,emptyErrors,false,
                            "Error: " + trdf, resultFormat);
              return new ValidateResult(schema,schemaFormat,schemaEngine,data,dataFormat,emptyErrors,false,
                      "Error: " + t,resultFormat);
            } else {
               return new ValidateResult(schema,schemaFormat,schemaEngine,data,dataFormat,emptyErrors,false,
                       "Error: " + tstr,resultFormat);

            }
        } else {
            return new ValidateResult(schema,schemaFormat,schemaEngine,data,dataFormat,emptyErrors,false,
                    "Error: " + t,resultFormat);
        }
    }

}
