package com.todolistapplicationspecialtopics.repository;

import com.todolistapplicationspecialtopics.model.ToDo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ToDoRepository extends ElasticsearchRepository<ToDo, String> {
    List<ToDo> findAll();

    List<ToDo> findByStatusAndImportance(String status, String importance);

    List<ToDo> findByStatus(String status);

    List<ToDo> findByImportance(String importance);
}
