package server.services;

import rx.Observable;
import server.domain.Todo;

import java.util.List;

public interface ITodoService {
    Observable<List<Todo>> getTodoList();

    Observable<Todo> addTodo(String text);

    Observable<Todo> deleteTodoById(Long todoId);
}
