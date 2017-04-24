package server.services;

import io.reactivex.Flowable;
import server.domain.Todo;

import java.util.List;

public interface TodoService {
    Flowable<List<Todo>> getTodoList();

    Flowable<Todo> addTodo(String text);

    Flowable<Todo> deleteTodoById(Long todoId);
}
