package server.services;

import reactor.core.publisher.Flux;
import server.domain.Todo;

import java.util.List;

public interface ITodoService {
    Flux<List<Todo>> getTodoList();

    Flux<Todo> addTodo(String text);

    Flux<Todo> deleteTodoById(Long todoId);
}
