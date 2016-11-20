package es.weso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.weso.schema.DataFormats;
import es.weso.schema.Schemas;

import static es.weso.ScalaConverters.asJava;

@RestController
public class LandingController {

  private static final Logger LOG = LoggerFactory.getLogger(LandingController.class);

    @RequestMapping("/")
    public ModelAndView landing() {
        LOG.info("/ Landing page access");
        return new ModelAndView("landing");
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
