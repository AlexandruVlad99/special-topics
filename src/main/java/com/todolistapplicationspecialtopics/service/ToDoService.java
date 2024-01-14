package com.todolistapplicationspecialtopics.service;

import com.todolistapplicationspecialtopics.dto.PatchToDoRequest;
import com.todolistapplicationspecialtopics.dto.ToDoResponse;
import com.todolistapplicationspecialtopics.dto.UpdateToDoRequest;
import com.todolistapplicationspecialtopics.exception.ToDoNotFoundException;
import com.todolistapplicationspecialtopics.model.ToDo;
import com.todolistapplicationspecialtopics.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDos(String status, String importance) {

        if (status != null && importance != null) {
            return toDoRepository.findByStatusAndImportance(status, importance);
        }

        if (status != null) {
            return toDoRepository.findByStatus(status);
        }

        if (importance != null) {
            return toDoRepository.findByImportance(importance);
        }

        return toDoRepository.findAll();
    }

    public ToDo addToDo(ToDo newToDo) {
        newToDo.setCreationDate(new Date());

        return toDoRepository.save(newToDo);
    }

    @Transactional
    public ToDo updateToDo(UpdateToDoRequest updateRequest) {
        ToDo existingToDo = toDoRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        existingToDo.setExplanation(updateRequest.getExplanation());
        existingToDo.setImportance(updateRequest.getImportance());
        existingToDo.setStatus(updateRequest.getStatus());

        return toDoRepository.save(existingToDo);
    }

    public void deleteToDo(String id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        toDoRepository.delete(toDo);
    }

    public ToDoResponse getToDoDetails(String id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        return mapToDoEntityToResponse(toDo);
    }

    public ToDoResponse patchToDo(String id, PatchToDoRequest patchToDoRequest) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found."));

        if (patchToDoRequest.getExplanation() != null) {
            toDo.setExplanation(patchToDoRequest.getExplanation());
        }

        if (patchToDoRequest.getImportance() != null) {
            toDo.setImportance(patchToDoRequest.getImportance());
        }

        if (patchToDoRequest.getStatus() != null) {
            toDo.setStatus(patchToDoRequest.getStatus());
        }

        toDoRepository.save(toDo);

        return mapToDoEntityToResponse(toDo);
    }

    private ToDoResponse mapToDoEntityToResponse(ToDo toDo) {
        ToDoResponse response = new ToDoResponse();
        response.setId(toDo.getId());
        response.setExplanation(toDo.getExplanation());
        response.setImportance(toDo.getImportance());
        response.setStatus(toDo.getStatus());
        response.setCreationDate(toDo.getCreationDate());
        return response;
    }
}

