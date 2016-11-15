package es.weso;

import es.weso.schema.Schemas;
import es.weso.schema.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scala.Option;
import scala.util.Try;


@RestController
public class SchemaCheckController {

    @RequestMapping("/schema/check")
    public SchemaCheckerResult schemaCheck(@RequestParam(value="schema") String schema,
                             @RequestParam(value="schemaFormat") String schemaFormat,
                             @RequestParam(value="schemaEngine") String schemaEngine,
                             @RequestParam(value="resultFormat") String resultFormat) {
        Option<String> base = Option.apply(null);
        Try<Schema> t = Schemas.fromString(schema,schemaFormat, schemaEngine, base);
        if (t.isSuccess()) {
            Schema s = t.get();
            Try<String> tstr = s.serialize(resultFormat);
            if (tstr.isSuccess()) {
              return new SchemaCheckerResult(schema,schemaFormat,resultFormat,tstr.get());
            } else {
               return new SchemaCheckerResult(schema,schemaFormat,resultFormat,
                       "Error: " + tstr);

            }
        } else
            return new SchemaCheckerResult(schema,schemaFormat,resultFormat,
                    "Error: " + t);

    }

}
