package server.services;

import reactor.core.publisher.Flux;
import server.domain.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TodoService implements ITodoService {
    private final ConcurrentHashMap<Long, Todo> todoById = new ConcurrentHashMap<>();
    private final AtomicLong lastId = new AtomicLong(0);

    public Flux<List<Todo>> getTodoList() {
        return Flux.create(s -> {
            s.next(Arrays.asList(todoById.values().toArray(new Todo[]{})));
            s.complete();
        });
    }

    @Override
    public Flux<Todo> addTodo(String text) {
        return Flux.create(s -> {
            long newId = lastId.incrementAndGet();
            Todo todo = new Todo(newId, text);
            this.todoById.put(todo.getId(), todo);
            s.next(todo);
            s.complete();
        });
    }

    @Override
    public Flux<Todo> deleteTodoById(Long todoId) {
        return Flux.create(s -> {
            Todo delTodo = todoById.get(todoId);
            todoById.remove(todoId);
            s.next(delTodo);
            s.complete();
        });
    }
}
