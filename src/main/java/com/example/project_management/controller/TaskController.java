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

    // Метод для відображення форми створення нового завдання
    @GetMapping("/{projectId}/new")
    public String showTaskCreateForm(@PathVariable Long projectId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projectId", projectId);
        return "tasks/create";
    }

    // Метод для створення нового завдання
    @PostMapping("/{projectId}")
    public String createTask(@PathVariable Long projectId, @Valid @ModelAttribute Task task,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "tasks/create";
        }
        taskService.saveTask(projectId, task);
        return "redirect:/projects/" + projectId;
    }

    // Метод для відображення форми редагування завдання
    @GetMapping("/{taskId}/edit")
    public String showTaskEditForm(@PathVariable Long taskId, Model model) {
        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);
        return "tasks/edit";
    }

    // Метод поновлення завдання
    @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable Long taskId, @Valid @ModelAttribute Task task,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "tasks/edit";
        }
        taskService.updateTask(taskId, task);
        return "redirect:/projects/" + task.getProject().getId();
    }

    // Метод видалення завдання
    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {
        Task task = taskService.findById(taskId);
        Long projectId = task.getProject().getId();
        taskService.deleteTask(taskId);
        return "redirect:/projects/" + projectId;
    }
}
