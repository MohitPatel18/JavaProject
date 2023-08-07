package com.project.todolistmanager.service;

import com.project.todolistmanager.domain.Todo;
import com.project.todolistmanager.repository.TodoRepository;
import com.project.todolistmanager.repository.entities.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createNewTodo(Todo todoReq) {
        TodoEntity todoEntityToPersist = new TodoEntity();
        todoEntityToPersist.setTitle(todoReq.getTitle());
        todoEntityToPersist.setDescription(todoReq.getDescription());
        TodoEntity newTodoEntity = todoRepository.save(todoEntityToPersist);
        return convert(newTodoEntity);
    }

    @Override
    public Todo getTodo(long id) {
        Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        return todoEntity.map(this::convert)
                .orElseThrow(() -> new RuntimeException("Todo With Id Not Found !"));
    }

    @Override
    public Todo updateTodo(Todo todoReq) {
        Optional<TodoEntity> todoEntity = todoRepository.findById(todoReq.getId());
        if (!todoEntity.isPresent()) {
            throw new RuntimeException("Todo With Id Not Found !");
        }
        todoEntity.get().setTitle(todoReq.getTitle());
        todoEntity.get().setDescription(todoReq.getDescription());
        return convert(todoRepository.save(todoEntity.get()));
    }

    @Override
    public void deleteTodo(long id) {
        Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        if (todoEntity.isPresent()) {
            todoRepository.delete(todoEntity.get());
        } else {
            throw new RuntimeException("Todo With Id Not Found !");
        }
    }

    private Todo convert(TodoEntity entity) {
        Todo todo = new Todo();
        todo.setId(entity.getId());
        todo.setTitle(entity.getTitle());
        todo.setDescription(entity.getDescription());
        todo.setCreatedBy(entity.getCreatedBy());
        todo.setCreatedOn(entity.getCreatedOn());
        todo.setLastModifiedOn(entity.getLastModifiedOn());
        todo.setLastModifiedBy(entity.getLastModifiedBy());
        return todo;
    }
}
