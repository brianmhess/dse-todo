package hessian.example.TodoDemo;

import java.util.*;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class TodoItemRepository {
    private MappingManager mappingManager;
    private Mapper<TodoItem> mapper;
    private Session session;

    @Autowired
    public TodoItemRepository(MappingManager mappingManager) {
        this.mappingManager = mappingManager;
    }

    @PostConstruct
    private void init() {
        mapper = mappingManager.mapper(TodoItem.class);
        session = mappingManager.getSession();

        String cqlFindByCategory = "SELECT * FROM todo WHERE category = ? ALLOW FILTERING";
        String cqlFindAll = "SELECT * FROM todo";
        psFindByCategory = session.prepare(cqlFindByCategory);
        psFindAll = session.prepare(cqlFindAll);
    }

    private PreparedStatement psFindByCategory = null;
    public List<TodoItem> findByCategory(String category) {
        BoundStatement bs = psFindByCategory.bind();
        bs.set(0, category, String.class);
        return mapper.map(session.execute(bs)).all();
    }

    private String cqlFindAll = "SELECT * FROM todo";
    private PreparedStatement psFindAll = null;
    public List<TodoItem> findAll() {
        BoundStatement bs = psFindAll.bind();
        return mapper.map(session.execute(bs)).all();
    }

    public void save(TodoItem todoItem) {
        mapper.save(todoItem);
    }

}
