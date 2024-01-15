package com.todolistapplicationspecialtopics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubTask {
    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Boolean)
    private boolean completed;
}
