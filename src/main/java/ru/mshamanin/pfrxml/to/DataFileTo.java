package ru.mshamanin.pfrxml.to;

public class DataFileTo extends BaseTo{
    private final String fileName;
    private final String body;

    public DataFileTo(String fileName, String body) {
        this.fileName = fileName;
        this.body = body;
    }

    public DataFileTo(Integer id, String fileName, String body) {
        super(id);
        this.fileName = fileName;
        this.body = body;
    }

    public String getFileName() {
        return fileName;
    }

    public String getBody() {
        return body;
    }
}
