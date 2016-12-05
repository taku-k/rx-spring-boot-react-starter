package server.services;

import rx.Observable;
import server.domain.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TodoServiceImpl implements TodoService {
    private final ConcurrentHashMap<Long, Todo> todoById = new ConcurrentHashMap<>();
    private final AtomicLong lastId = new AtomicLong(0);

    public Observable<List<Todo>> getTodoList() {
        return Observable.just(Arrays.asList(todoById.values().toArray(new Todo[]{})));
    }

    public Observable<Todo> addTodo(String text) {
        return Observable.create(s -> {
            long newId = lastId.incrementAndGet();
            Todo todo = new Todo(newId, text);
            this.todoById.put(todo.getId(), todo);
            s.onNext(todo);
            s.onCompleted();
        });
    }

    public Observable<Todo> deleteTodoById(Long todoId) {
        return Observable.create(s -> {
            Todo delTodo = todoById.get(todoId);
            todoById.remove(todoId);
            s.onNext(delTodo);
            s.onCompleted();
        });
    }
}
