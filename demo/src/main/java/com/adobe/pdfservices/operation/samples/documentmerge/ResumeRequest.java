package com.adobe.pdfservices.operation.samples.documentmerge;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonNaming;



public class ResumeRequest {


    // Getters and Setters
    private String template_id;
    private PersonalInformation personal_information;
    private String job_title;
    private String career_objective;
    private List<String> skills;
    private List<Education> education;
    private List<Experience> experience;
    private List<Achievement> achievements;

    // Getters and Setters
    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public PersonalInformation getPersonal_information() {
        return personal_information;
    }

    public void setPersonal_information(PersonalInformation personal_information) {
        this.personal_information = personal_information;
    }


    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCareer_objective() {
        return career_objective;
    }

    public void setCareer_objective(String career_objective) {
        this.career_objective = career_objective;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    @Override
    public String toString() {
        return "ResumeRequest{" +
                "template_id='" + template_id + '\'' +
                ", personal_information=" + personal_information +
                ", job_title='" + job_title + '\'' +
                ", career_objective='" + career_objective + '\'' +
                ", skills=" + skills +
                ", education=" + education +
                ", experience=" + experience +
                ", achievements=" + achievements +
                '}';
    }
}
