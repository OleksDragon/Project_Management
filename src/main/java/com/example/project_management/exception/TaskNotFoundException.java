package com.example.project_management.exception;

public class TaskNotFoundException  extends RuntimeException {

    public TaskNotFoundException(Long taskId) {
        super("Task with ID: " + taskId + " not found");
    }
}
