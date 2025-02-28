package com.example.project_management.controller;

import com.example.project_management.model.Task;
import com.example.project_management.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/{projectId}/new")
    public String showTaskCreateForm(@PathVariable Long projectId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projectId", projectId);
        return "tasks/create";
    }

    @PostMapping("/{projectId}")
    public String createTask(@PathVariable Long projectId, @Valid @ModelAttribute Task task,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "tasks/create";
        }
        taskService.saveTask(projectId, task);
        return "redirect:/projects/" + projectId;
    }
}
