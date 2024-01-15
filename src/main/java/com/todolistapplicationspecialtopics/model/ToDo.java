package com.todolistapplicationspecialtopics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Document(indexName = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String explanation;

    @Field(type = FieldType.Keyword)
    private Importance importance;

    @Field(type = FieldType.Keyword)
    private Status status;

    @Field(type = FieldType.Long)
    private Date creationDate;

    @Field(type = FieldType.Nested)
    private List<SubTask> subTasks;
}

