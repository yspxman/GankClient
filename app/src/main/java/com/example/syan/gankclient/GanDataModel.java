package com.example.syan.gankclient;

import java.util.List;
import java.util.ArrayList;

public class GanDataModel {
    private boolean error;

    private ArrayList<SisterModel> results;

    public ArrayList<SisterModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<SisterModel> results) {
        this.results = results;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }
}
