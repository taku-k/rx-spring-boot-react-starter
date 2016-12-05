package server.services;

import org.junit.Test;
import rx.observers.TestSubscriber;
import server.domain.Todo;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class TodoServiceTest {
    @Test
    public void testGetEmptyTodoList() {
        TodoService todoService = new TodoServiceImpl();
        TestSubscriber<List<Todo>> testSubscriber = new TestSubscriber<>();
        todoService.getTodoList().subscribe(testSubscriber);

        // No emit error
        testSubscriber.assertNoErrors();
        List<Todo> emptyTodo = testSubscriber.getOnNextEvents().get(0);
        assertThat(emptyTodo)
                .as("Get empty todo list")
                .isEmpty();
    }

    @Test
    public void testOneTodoList() {
        TodoService todoService = new TodoServiceImpl();
        // Add one text to the list
        todoService.addTodo("todo1").toBlocking().first();
        TestSubscriber<List<Todo>> testSubscriber = new TestSubscriber<>();
        todoService.getTodoList().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<Todo> oneTodo = testSubscriber.getOnNextEvents().get(0);
        assertThat(oneTodo)
                .as("List contains one message `todo1`")
                .extracting(Todo::getText)
                .containsExactly("todo1");
    }
}
