package startup.poc.saisiedetemps.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import startup.poc.saisiedetemps.dto.CreateProjectRequest;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.services.ProjectService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
@Tag(name = "Projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    @Operation(summary = "Get all projects")
    List<Project> findAllProjects(){
        return this.projectService.findAllProjects();
    }

    @PostMapping("/projects")
    @Operation(summary = "Create project")
    Project createProject(@RequestBody CreateProjectRequest projectRequest, @AuthenticationPrincipal(errorOnInvalidType = true) User user) {
        System.out.println(user);
       return this.projectService.createProject(projectRequest, user);

    }
}