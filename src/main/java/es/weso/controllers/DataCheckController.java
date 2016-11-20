package es.weso.controllers;

import es.weso.rdf.RDFReader;
import es.weso.rdf.jena.RDFAsJenaModel;
import es.weso.results.DataCheckResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scala.Option;
import scala.util.Try;

import static es.weso.Config.DATA_CHECK_URI;

@RestController
public class DataCheckController {

    @RequestMapping(DATA_CHECK_URI)
    public DataCheckResult dataCheck(@RequestParam(value="data") String data,
                             @RequestParam(value="dataFormat") String dataFormat,
                             @RequestParam(value="resultFormat") String resultFormat) {
        Option<String> base = Option.apply(null);
        Try<RDFAsJenaModel> t = RDFAsJenaModel.fromChars(data,dataFormat, base);
        if (t.isSuccess()) {
            RDFReader s = t.get();
            String tstr = s.serialize(resultFormat);
              return new DataCheckResult(data,dataFormat,resultFormat,tstr);
        } else
            return new DataCheckResult(data,dataFormat,resultFormat,"Error: " + t);
    }

}
