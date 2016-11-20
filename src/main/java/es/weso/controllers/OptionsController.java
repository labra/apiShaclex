package es.weso.controllers;

import static es.weso.Config.API_URI;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.weso.Schema2Java;

@RestController
public class OptionsController {

    @RequestMapping(API_URI + "availableSchemaFormats")
    public List<String> availableSchemaFormats() {
        return Schema2Java.availableSchemaFormats();
    }

    @RequestMapping(API_URI + "availableSchemaEngines")
    public List<String> availableSchemaEngines() {
        return Schema2Java.availableSchemaEngines();
    }

    @RequestMapping(API_URI + "availableDataFormats")
    public List<String> availableDataFormats() {
        return Schema2Java.availableDataFormats();
    }

}
