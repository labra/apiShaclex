package es.weso.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.weso.Schema2Java;
import es.weso.schema.DataFormats;
import es.weso.schema.Schemas;

import static es.weso.utils.ScalaConverters.asJava;

@RestController
public class MainController {

  private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public ModelAndView landing() {
        LOG.info("/ Landing page access");
        return new ModelAndView("landing");
      }

    @RequestMapping("/schemaCheck")
    public ModelAndView schemaCheck() {
    	ModelAndView mav = new ModelAndView("schemaCheck");
        mav.addObject("schemaEngines", Schema2Java.availableSchemaEngines());
        mav.addObject("schemaFormats", Schema2Java.availableSchemaFormats());
        mav.addObject("dataFormats", Schema2Java.availableDataFormats());
        LOG.info("/ SchemaCheck. Engines: " + Schema2Java.availableSchemaEngines());
        return mav;
    }
    
    @RequestMapping("/dataCheck")
    public ModelAndView dataCheck() {
    	ModelAndView mav = new ModelAndView("dataCheck");
        mav.addObject("dataFormats", Schema2Java.availableDataFormats());
        LOG.info("/ Data check. DataFormats: " + Schema2Java.availableDataFormats());
        return mav;
    }
    
    @RequestMapping("/options")
    public ModelAndView options() {
        LOG.info("/options access");
        ModelAndView mav = new ModelAndView("options");
        mav.addObject("engines", asJava(Schemas.availableSchemaNames()));
        mav.addObject("schemaFormats", asJava(Schemas.availableFormats()));
        mav.addObject("dataFormats", asJava(DataFormats.formatNames()));
        return mav;
      }
}
