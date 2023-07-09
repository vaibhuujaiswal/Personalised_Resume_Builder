package com.adobe.pdfservices.operation.samples.documentmerge;


import com.fasterxml.jackson.databind.annotation.JsonNaming;



public class Experience {
    private String company_name;
    private String passing_year;
    private String responsibilities;

    // Getters and Setters
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPassing_year() {
        return passing_year;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "company_name='" + company_name + '\'' +
                ", passing_year='" + passing_year + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                '}';
    }
}