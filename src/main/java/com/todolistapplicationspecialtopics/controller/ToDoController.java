package com.todolistapplicationspecialtopics.controller;

import com.todolistapplicationspecialtopics.dto.ToDoResponse;
import com.todolistapplicationspecialtopics.dto.UpdateStatusRequest;
import com.todolistapplicationspecialtopics.dto.UpdateToDoRequest;
import com.todolistapplicationspecialtopics.model.ToDo;
import com.todolistapplicationspecialtopics.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<List<ToDo>> getAllToDos(@RequestParam(name = "status", required = false) String status,
                                                  @RequestParam(name = "importance", required = false) String importance) {
        List<ToDo> todos = toDoService.getAllToDos(status, importance);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo newToDo) {
        ToDo savedToDo = toDoService.addToDo(newToDo);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable Long id, @RequestBody UpdateToDoRequest updateRequest) {
        updateRequest.setId(id);
        ToDo updatedToDo = toDoService.updateToDo(updateRequest);
        return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponse> getToDoDetails(@PathVariable Long id) {
        ToDoResponse toDoDetails = toDoService.getToDoDetails(id);
        return ResponseEntity.ok(toDoDetails);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDoResponse> updateToDoStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest updateStatusRequest) {
        ToDoResponse updatedToDo = toDoService.updateToDoStatus(id, updateStatusRequest);
        return ResponseEntity.ok(updatedToDo);
    }

}