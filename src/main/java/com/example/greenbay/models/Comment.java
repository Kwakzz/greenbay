package com.example.greenbay.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String content;
    private String authorId;
    private int noOflikes;
    private List<Comment> replies = new ArrayList<>();
    private String threadId;

    public Comment(String content, String authorId, String threadId) {
        this.content = content;
        this.authorId = authorId;
        this.noOflikes = 0;
        this.threadId = threadId;
    }


    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public String getAuthor() {
        return this.authorId;
    }

    public int getNoOfLikes() {
        return this.noOflikes;
    }

    public List<Comment> getReplies() {
        return this.replies;
    }

    public String getThread() {
        return this.threadId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String authorId) {
        this.authorId = authorId;
    }

    public void setNoOfLikes(int noOflikes) {
        this.noOflikes = noOflikes;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public void setThread(String threadId) {
        this.threadId = threadId;
    }


}