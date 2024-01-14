package com.todolistapplicationspecialtopics.repository;

import com.todolistapplicationspecialtopics.model.ToDo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ToDoRepository extends ElasticsearchRepository<ToDo, String> {
    List<ToDo> findAll();
}
