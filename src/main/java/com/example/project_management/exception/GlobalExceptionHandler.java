package com.example.project_management.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProjectNotFoundException.class)
    public String handleProjectNotFound(ProjectNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/404";
    }
}
