package startup.poc.saisiedetemps.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import startup.poc.saisiedetemps.auth.AuthenticationService;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.repositories.ProjectRepository;
import startup.poc.saisiedetemps.services.ProjectService;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private ProjectRepository projectRepository;


    public List<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }


    @Transactional
    public Project createProject(Project project,String username) {
        User currentUser = this.authService.getLoggedInUserInfo(username);
        project.setManager(currentUser);
        return this.projectRepository.save(project);
    }

    public List<Project>  findProjectsOfManager(Long managerId) {
        return this.projectRepository.findByManagerUserId(managerId);
    }
}
