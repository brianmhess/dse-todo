package com.example.fabrikam.TodoDemo;

import java.util.*;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TodoItemRepository extends CassandraRepository<TodoItem, Long> {

    //@Query(allowFiltering = true)
    @Query("SELECT * FROM todo WHERE category = ?0 ALLOW FILTERING")
    List<TodoItem> findByCategory(String category);

}
