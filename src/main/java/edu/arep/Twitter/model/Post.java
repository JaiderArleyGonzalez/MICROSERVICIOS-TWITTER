package edu.arep.Twitter.model;

import java.sql.Date;

public class Post {
    private String owner;
    private String message;
    private Date creationDate;

    public Post() {

    }

    public Post(String owner, String message, Date creationDate) {
        this.owner = owner;
        this.message = message.length() > 140 ? message.substring(0, 140) : message;
        this.creationDate = creationDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message.length() > 140) {
            this.message = message.substring(0, 140);
        } else {
            this.message = message;
        }
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
