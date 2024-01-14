package com.todolistapplicationspecialtopics.controller;

import com.todolistapplicationspecialtopics.model.ToDo;
import com.todolistapplicationspecialtopics.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo newToDo) {
        ToDo savedToDo = toDoService.saveToDo(newToDo);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }

}