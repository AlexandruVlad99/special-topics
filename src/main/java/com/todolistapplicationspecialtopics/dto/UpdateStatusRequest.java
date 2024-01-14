package com.todolistapplicationspecialtopics.dto;

import com.todolistapplicationspecialtopics.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest {
    private Status status;
}
