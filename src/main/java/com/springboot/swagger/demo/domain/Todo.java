package com.springboot.swagger.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Todo implements Serializable {

    @Id
    private long id;

    private boolean isCompleted;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expectedBeginDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expectedCompletionDate;

    //GETTERS AND SETTERS.
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpectedBeginDate() {
        return expectedBeginDate;
    }

    public void setExpectedBeginDate(Date expectedBeginDate) {
        this.expectedBeginDate = expectedBeginDate;
    }

    public Date getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(Date expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }
}
