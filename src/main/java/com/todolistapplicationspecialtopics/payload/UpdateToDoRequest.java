package com.todolistapplicationspecialtopics.payload;

import com.todolistapplicationspecialtopics.model.Importance;
import com.todolistapplicationspecialtopics.model.Status;
import com.todolistapplicationspecialtopics.model.SubTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateToDoRequest {
    private String explanation;
    private Importance importance;
    private Status status;
    private List<SubTask> subTasks;
}
