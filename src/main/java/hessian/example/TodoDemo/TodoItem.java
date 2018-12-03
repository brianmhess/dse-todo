package hessian.example.TodoDemo;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import jnr.ffi.annotations.In;

@Table(name = "todo")
public class TodoItem {

    @Column
    private Integer id;
    @Column
    private String category;
    @PartitionKey(0)
    @Column
    private String name;
    @Column
    private boolean complete;

    public TodoItem() {}

    public TodoItem(String category, String name) {
        this.category = category;
        this.name = name;
        this.complete = false;
    }

    @Override
    public String toString() {
        return String.format(
                "TodoItem[id=%d, category='%s', name='%s', complete='%b']",
                id, category, name, complete);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        return;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        return;
    }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        return;
    }

    public boolean isComplete() {
        return complete;
    }
    
    public void setComplete(boolean complete) {
        this.complete = complete;
        return;
    }
}
