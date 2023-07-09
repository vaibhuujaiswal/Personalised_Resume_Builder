package com.adobe.pdfservices.operation.samples.documentmerge;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class resumeRequestSerializer extends JsonSerializer<ResumeRequest> {

    @Override
    public void serialize(ResumeRequest myClass, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        //Personal Information and Job title
        jsonGenerator.writeStringField("Name", myClass.getPersonal_information().getName());
        jsonGenerator.writeStringField("LastName", myClass.getPersonal_information().getLast_name());
        jsonGenerator.writeStringField("JobTitle", myClass.getJob_title());
        jsonGenerator.writeStringField("EmailAddress", myClass.getPersonal_information().getEmail_address());
        jsonGenerator.writeStringField("PhoneNumber", myClass.getPersonal_information().getPhone_number());
        jsonGenerator.writeStringField("LinkedIn", generateLinkedInLink(myClass.getPersonal_information().getLinkedin_url()));

        jsonGenerator.writeStringField("Summary", myClass.getCareer_objective());
        //
        jsonGenerator.writeArrayFieldStart("Experience");
        for (Experience exp : myClass.getExperience()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("CompanyName", exp.getCompany_name());
            jsonGenerator.writeStringField("Year", exp.getPassing_year());
            jsonGenerator.writeStringField("Description", exp.getResponsibilities());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        //education start
        jsonGenerator.writeArrayFieldStart("Education");
        for (Education sch : myClass.getEducation()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("SchoolName", sch.getSchool_name());
            jsonGenerator.writeStringField("Year", sch.getPassing_year());
            jsonGenerator.writeStringField("Description", sch.getDescription());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        //education end

        //skills start
        jsonGenerator.writeFieldName("Skills");
        jsonGenerator.writeStartArray();
        for (String skill : myClass.getSkills()) {
            jsonGenerator.writeString(skill);
        }
        jsonGenerator.writeEndArray();
        //skills end

        //Achievements Column
        jsonGenerator.writeArrayFieldStart("Achievements");
        for (Achievement ach : myClass.getAchievements()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("Type", ach.getField());
            jsonGenerator.writeStringField("Description", ach.getAwards());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }

    private static String generateLinkedInLink(String url) {
        String linkedInText = "linkedIn";
        return "<a href=\"" + url + "\">" + linkedInText + "</a>";
    }
}




