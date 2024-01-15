package com.todolistapplicationspecialtopics.service;

import com.todolistapplicationspecialtopics.exception.ToDoNotFoundException;
import com.todolistapplicationspecialtopics.model.ToDo;
import com.todolistapplicationspecialtopics.payload.AddToDoRequest;
import com.todolistapplicationspecialtopics.payload.UpdateToDoRequest;
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

    public ToDo addToDo(AddToDoRequest newToDo) {
        ToDo toDo = ToDo.builder()
                .importance(newToDo.getImportance())
                .explanation(newToDo.getExplanation())
                .status(newToDo.getStatus())
                .subTasks(newToDo.getSubTasks())
                .creationDate(new Date())
                .build();

        return toDoRepository.save(toDo);
    }

    @Transactional
    public ToDo updateToDo(String id, UpdateToDoRequest updateRequest) {
        ToDo existingToDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        existingToDo.setExplanation(updateRequest.getExplanation());
        existingToDo.setImportance(updateRequest.getImportance());
        existingToDo.setStatus(updateRequest.getStatus());
        existingToDo.setSubTasks(updateRequest.getSubTasks());

        return toDoRepository.save(existingToDo);
    }

    public void deleteToDo(String id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));

        toDoRepository.delete(toDo);
    }

    public ToDo getToDoDetails(String id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo item not found"));
    }

    public ToDo patchToDo(String id, UpdateToDoRequest patchToDoRequest) {
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

        if (patchToDoRequest.getSubTasks() != null) {
            toDo.setSubTasks(patchToDoRequest.getSubTasks());
        }

        toDoRepository.save(toDo);

        return toDo;
    }
}

