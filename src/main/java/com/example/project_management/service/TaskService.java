package com.example.project_management.service;

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
    private ProjectService projectService;

    public Task saveTask(Long projectId, Task task) {
        Project project = projectService.getProjectById(projectId);
        task.setProject(project);
        return taskRepository.save(task);
    }

}
