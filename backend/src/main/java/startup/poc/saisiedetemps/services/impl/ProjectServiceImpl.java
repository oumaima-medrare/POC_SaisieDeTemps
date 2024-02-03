package startup.poc.saisiedetemps.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import startup.poc.saisiedetemps.auth.AuthenticationService;
import startup.poc.saisiedetemps.dto.CreateProjectRequest;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.repositories.ProjectRepository;
import startup.poc.saisiedetemps.services.ProjectService;
import startup.poc.saisiedetemps.services.UserService;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;


    public List<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }


    @Transactional
    public Project createProject(CreateProjectRequest projectRequest, User user ) {
        Project project = new Project();
        project.setDescription(projectRequest.getDescription());
        project.setTitle(projectRequest.getTitle());
        project.setManager(user);
        return this.projectRepository.save(project);
    }

    public List<Project>  findProjectsOfManager(Long managerId) {
        return this.projectRepository.findByManagerId(managerId);
    }
}
