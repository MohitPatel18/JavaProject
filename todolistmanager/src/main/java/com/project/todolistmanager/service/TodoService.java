package com.project.todolistmanager.service;

import com.project.todolistmanager.domain.Todo;

public interface TodoService {
    Todo createNewTodo(Todo todoReq);

    Todo getTodo(long id);

    Todo updateTodo(Todo todoReq);

    void deleteTodo(long id);
}
