package startup.poc.saisiedetemps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.services.ProjectService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    List<Project> findAllProjects(){
        return this.projectService.findAllProjects();
    }

    @PostMapping("/projects/{username}")
    Project createTask(@RequestBody Project project,@PathVariable String username) {
        return this.projectService.createProject(project,username);
    }
}