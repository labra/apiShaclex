package es.weso.results;

public class SchemaCheckResult {

    private final String schema;
    private final String format;
    private final String outFormat;
    private final String result;

    public SchemaCheckResult(String schema, String format, String outFormat, String result) {
        this.schema = schema;
        this.format = format;
        this.outFormat = outFormat;
        this.result = result;
    }

    public String getSchema() {
        return schema;
    }

    public String getFormat() {
        return format;
    }

    public String getOutFormat() {
        return outFormat;
    }

    public String getResult() {
        return result;
    }
}
