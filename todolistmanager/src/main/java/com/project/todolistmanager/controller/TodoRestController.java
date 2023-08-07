package com.project.todolistmanager.controller;

import com.project.todolistmanager.domain.Todo;
import com.project.todolistmanager.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/todo")
public class TodoRestController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Todo todoReq) {
        try {
            return ResponseEntity.ok(todoService.createNewTodo(todoReq));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity<?> read(@RequestParam long id) {
        try {
            return ResponseEntity.ok(todoService.getTodo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Todo todoReq) {
        try {
            return ResponseEntity.ok(todoService.updateTodo(todoReq));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.ok("Successfully Deleted : " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }

}
