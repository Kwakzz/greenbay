package com.example.greenbay.models;


public class Project {

    private String id;
    private String title;
    private String description;
    private String ownerId;
    private String status;
    private String deadline;

    public Project() {
    }

    public Project(String title, String description, String deadline, String ownerId) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.ownerId = ownerId;
        this.status = "open";
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeadline() {
        return deadline;
    }


    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

}