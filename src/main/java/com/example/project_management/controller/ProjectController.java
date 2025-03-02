package com.example.project_management.controller;

import com.example.project_management.model.Project;
import com.example.project_management.model.ProjectStatus;
import com.example.project_management.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getAllProjects(@RequestParam(required = false) ProjectStatus status, Model model) {
        model.addAttribute("projects", projectService.getAllProjects(status));
        return "projects/list";
    }

    @GetMapping("/{id}")
    public String getProjectDetails ( @PathVariable Long id, Model model){
        model.addAttribute("project", projectService.getProjectById(id));
        return "projects/details";
    }

    @GetMapping("/new")
    public String showCreateForm (Model model) {
        model.addAttribute("project", new Project());
        return "projects/create";
    }

    @PostMapping
    public String createProject (@Valid @ModelAttribute Project project, BindingResult result){
        if (result.hasErrors()) {
            return "projects/create";
        }
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm (@PathVariable Long id, Model model){
        model.addAttribute("project", projectService.getProjectById(id));
        return "projects/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProject ( @PathVariable long id, @Valid @ModelAttribute Project project,
                                  BindingResult result){
        if (result.hasErrors()) {
            return "projects/edit";
        }
        project.setId(id);
        projectService.saveProject(project);
        return "redirect:/projects/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}
