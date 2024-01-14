package com.todolistapplicationspecialtopics.dto;

import com.todolistapplicationspecialtopics.model.Importance;
import com.todolistapplicationspecialtopics.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateToDoRequest {
    private String id;
    private String explanation;
    private Importance importance;
    private Status status;
}
