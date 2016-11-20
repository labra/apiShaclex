package es.weso.results;

public class DataCheckResult {

    private final String data;
    private final String format;
    private final String outFormat;
    private final String result;

    public DataCheckResult(String data, String format, String outFormat, String result) {
        this.data = data;
        this.format = format;
        this.outFormat = outFormat;
        this.result = result;
    }

    public String getData() {
        return data;
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
