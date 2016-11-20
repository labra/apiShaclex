package es.weso;

import java.util.List;

public class ValidateResult {

    private final String schema;
    private final String schemaFormat;
    private final String schemaEngine;
    private final String data;
    private final String dataFormat;
    private final List<String> errors;
    private final Boolean isValid;
    private final String result;
    private final String resultFormat;

    public ValidateResult(String schema,
                          String schemaFormat,
                          String schemaEngine,
                          String data,
                          String dataFormat,
                          List<String> errors,
                          Boolean isValid,
                          String result,
                          String resultFormat) {
        this.schema = schema;
        this.schemaFormat = schemaFormat;
        this.schemaEngine = schemaEngine;
        this.data = data;
        this.dataFormat = dataFormat;
        this.errors = errors;
        this.isValid = isValid;
        this.result = result;
        this.resultFormat = resultFormat;
    }

    public String getSchema() {
        return schema;
    }

    public String getSchemaFormat() {
        return schemaFormat;
    }

    public String getSchemaEngine() {
        return schemaEngine;
    }

    public String getData() {
        return data;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public List<String> getErrors() {
        return errors;
    }

    public Boolean getValid() {
        return isValid;
    }

    public String getResult() {
        return result;
    }

    public String getResultFormat() {
        return resultFormat;
    }
}
