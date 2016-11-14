package server.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.domain.Todo;
import server.services.ITodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoResource {
    @Autowired
    ITodoService todoService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> list() {
        return todoService.getTodoList().blockFirst();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo add(@RequestBody Todo input) {
        return todoService.addTodo(input.getText()).blockFirst();
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE)
    public Todo del(@PathVariable Long todoId) {
        return todoService.deleteTodoById(todoId).blockFirst();
    }
}
