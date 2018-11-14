package com.example.fabrikam.TodoDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@RestController
public class TodoDemoRestController {

    @Autowired
    private TodoItemRepository repository;

    @RequestMapping("rest/hello")
    public String hello() {
	StringBuilder response = new StringBuilder("<html><body>");
	response.append("<H1>Hello World!</H1>");
	response.append("</body></html>");
	return response.toString();
    }

    @RequestMapping("rest/")
    public List<TodoItem> all() {
        ArrayList<TodoItem> todoList = (ArrayList<TodoItem>) repository.findAll();
	return todoList;
    }


    @RequestMapping(value = "rest/category/{category}", method = RequestMethod.GET)
    public List<TodoItem> category(@PathVariable String category) {
        ArrayList<TodoItem> todoList = (ArrayList<TodoItem>) repository.findByCategory(category);
	return todoList;
    }

}
