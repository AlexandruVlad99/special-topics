package com.todolistapplicationspecialtopics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    @Id
    private String id;
    private String explanation;
    private Importance importance;
    private Status status;
    private Date creationDate;
}

