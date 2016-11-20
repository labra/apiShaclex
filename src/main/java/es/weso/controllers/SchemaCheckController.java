package es.weso.controllers;

import es.weso.schema.Schemas;
import es.weso.results.SchemaCheckResult;
import es.weso.schema.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scala.Option;
import scala.util.Try;
import static es.weso.Config.SCHEMA_CHECK_URI;

@RestController
public class SchemaCheckController {

    @RequestMapping(SCHEMA_CHECK_URI)
    public SchemaCheckResult schemaCheck(@RequestParam(value="schema") String schema,
                                         @RequestParam(value="schemaFormat") String schemaFormat,
                                         @RequestParam(value="schemaEngine") String schemaEngine,
                                         @RequestParam(value="resultFormat") String resultFormat) {
        Option<String> base = Option.apply(null);
        Try<Schema> t = Schemas.fromString(schema,schemaFormat, schemaEngine, base);
        if (t.isSuccess()) {
            Schema s = t.get();
            Try<String> tstr = s.serialize(resultFormat);
            if (tstr.isSuccess()) {
              return new SchemaCheckResult(schema,schemaFormat,resultFormat,tstr.get());
            } else {
               return new SchemaCheckResult(schema,schemaFormat,resultFormat,
                       "Error: " + tstr);

            }
        } else
            return new SchemaCheckResult(schema,schemaFormat,resultFormat,
                    "Error: " + t);

    }

}
