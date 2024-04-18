package com.example.greenbay.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "threads")

public class Thread {
    
    @Id
    private String id;
    private String title;
    private String description;
    private String authorId;
    private String dateCreated;
    private List<Comment> comments = new ArrayList<>();

    public Thread(String title, String description, String authorId) {
        this.title = title;
        this.description = description;
        this.authorId = authorId;
        this.dateCreated = new java.util.Date().toString();
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAuthor() {
        return this.authorId;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String authorId) {
        this.authorId = authorId;
    }

}