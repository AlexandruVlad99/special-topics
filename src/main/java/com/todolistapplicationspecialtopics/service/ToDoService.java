package com.todolistapplicationspecialtopics.service;

import com.todolistapplicationspecialtopics.dto.ToDoResponse;
import com.todolistapplicationspecialtopics.dto.UpdateStatusRequest;
import com.todolistapplicationspecialtopics.dto.UpdateToDoRequest;
import com.todolistapplicationspecialtopics.exception.BadRequestException;
import com.todolistapplicationspecialtopics.exception.ToDoNotFoundException;
import com.todolistapplicationspecialtopics.model.Status;
import com.todolistapplicationspecialtopics.model.ToDo;
import com.todolistapplicationspecialtopics.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDo addToDo(ToDo newToDo) {
        return toDoRepository.save(newToDo);
    }

    @Transactional
    public ToDo updateToDo(UpdateToDoRequest updateRequest) {
        ToDo existingToDo = toDoRepository.findById(String.valueOf(updateRequest.getId()))
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        existingToDo.setExplanation(updateRequest.getExplanation());
        existingToDo.setImportance(updateRequest.getImportance());
        existingToDo.setStatus(updateRequest.getStatus());

        return toDoRepository.save(existingToDo);
    }

    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        toDoRepository.delete(toDo);
    }

    public ToDoResponse getToDoDetails(Long id) {
        ToDo toDo = toDoRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        return mapToDoEntityToResponse(toDo);
    }

    public ToDoResponse updateToDoStatus(Long id, UpdateStatusRequest updateStatusRequest) {
        ToDo toDo = toDoRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        Status newStatus = updateStatusRequest.getStatus();
        if (newStatus == null || !Status.isValidStatus(newStatus)) {
            throw new BadRequestException("Invalid status provided.");
        }

        toDo.setStatus(newStatus);
        toDoRepository.save(toDo);

        return mapToDoEntityToResponse(toDo);
    }


    private ToDoResponse mapToDoEntityToResponse(ToDo toDo) {
        ToDoResponse response = new ToDoResponse();
        response.setId(Long.valueOf(toDo.getId()));
        response.setExplanation(toDo.getExplanation());
        response.setImportance(toDo.getImportance());
        response.setStatus(toDo.getStatus());
        return response;
    }
}

