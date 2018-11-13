package com.example.fabrikam.TodoDemo;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TodoItemRepository extends CassandraRepository<TodoItem, Long> {

}
