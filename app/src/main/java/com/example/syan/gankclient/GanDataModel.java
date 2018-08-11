package com.example.syan.gankclient;

import java.util.List;

public class GanDataModel {
    private boolean error;

    private List<SisterModel> results;

    public List<SisterModel> getResults() {
        return results;
    }

    public void setResults(List<SisterModel> results) {
        this.results = results;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }
}
