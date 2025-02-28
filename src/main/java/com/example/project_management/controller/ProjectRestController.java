package com.example.project_management.controller;

import com.example.project_management.model.Project;
import com.example.project_management.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects(null);
    }

    @PostMapping
    public Project createProject(@Valid @RequestBody Project project) {
        return projectService.saveProject(project);
    }
}
