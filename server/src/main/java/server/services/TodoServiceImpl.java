package server.services;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import server.domain.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TodoServiceImpl implements TodoService {
    private final ConcurrentHashMap<Long, Todo> todoById = new ConcurrentHashMap<>();
    private final AtomicLong lastId = new AtomicLong(0);

    public Flowable<List<Todo>> getTodoList() {
        return Flowable.just(Arrays.asList(todoById.values().toArray(new Todo[]{})));
    }

    public Flowable<Todo> addTodo(String text) {
        return Flowable.create(emitter -> {
            long newId = lastId.incrementAndGet();
            Todo todo = new Todo(newId, text);
            this.todoById.put(todo.getId(), todo);
            emitter.onNext(todo);
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    public Flowable<Todo> deleteTodoById(Long todoId) {
        return Flowable.create(emitter -> {
            Todo delTodo = todoById.get(todoId);
            todoById.remove(todoId);
            emitter.onNext(delTodo);
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
    }
}
