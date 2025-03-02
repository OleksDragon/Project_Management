package com.example.project_management.service;

import com.example.project_management.exception.TaskNotFoundException;
import com.example.project_management.model.Project;
import com.example.project_management.model.Task;
import com.example.project_management.repository.ProjectRepository;
import com.example.project_management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Task saveTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        task.setProject(project);
        return taskRepository.save(task);
    }

    // Оновлення існуючого завдання
    public void updateTask(Long taskId, Task task) {
        task.setId(taskId);
        taskRepository.save(task);
    }

    // Видалення завдання
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    // Пошук завдання з ID
    public Task findById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
