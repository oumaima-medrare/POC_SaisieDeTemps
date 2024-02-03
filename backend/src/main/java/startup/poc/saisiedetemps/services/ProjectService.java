package startup.poc.saisiedetemps.services;

import java.util.List;

import startup.poc.saisiedetemps.dto.CreateProjectRequest;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.User;

public interface ProjectService {

    public List<Project> findAllProjects();

    public Project createProject(CreateProjectRequest projectRequest, User user);

    public List<Project>  findProjectsOfManager(Long managerId);
}