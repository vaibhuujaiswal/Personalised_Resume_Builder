package com.adobe.pdfservices.operation.samples.documentmerge;

import com.fasterxml.jackson.databind.annotation.JsonNaming;




public class Education {
    private String school_name;
    private String passing_year;
    private String description;

    // Getters and Setters
    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getPassing_year() {
        return passing_year;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Education{" +
                "school_name='" + school_name + '\'' +
                ", passing_year='" + passing_year + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
