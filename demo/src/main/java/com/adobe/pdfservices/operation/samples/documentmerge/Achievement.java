package com.adobe.pdfservices.operation.samples.documentmerge;
import com.fasterxml.jackson.databind.annotation.JsonNaming;



public class Achievement implements java.io.Serializable{
    private String field;
    private String awards;

    // Getters and Setters
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "field='" + field + '\'' +
                ", awards='" + awards + '\'' +
                '}';
    }
}
