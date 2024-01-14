package com.todolistapplicationspecialtopics.service;

import com.todolistapplicationspecialtopics.model.ToDo;
import com.todolistapplicationspecialtopics.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ToDo saveToDo(ToDo newToDo) {
        return toDoRepository.save(newToDo);
    }

}

