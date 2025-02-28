package com.example.project_management.repository;

import com.example.project_management.model.Project;
import com.example.project_management.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Поиск проектов по статусу
    List<Project> findByStatus(ProjectStatus status);
}
