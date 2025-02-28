package com.example.project_management.service;

import com.example.project_management.exception.ProjectNotFoundException;
import com.example.project_management.model.Project;
import com.example.project_management.model.ProjectStatus;
import com.example.project_management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects(ProjectStatus status) {
        return status != null ? projectRepository.findByStatus(status) : projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
