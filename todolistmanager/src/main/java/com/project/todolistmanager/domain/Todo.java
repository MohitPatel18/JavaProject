package com.project.todolistmanager.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Todo {
    private long id;
    private String title;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String lastModifiedBy;
    private Date lastModifiedOn;

}
