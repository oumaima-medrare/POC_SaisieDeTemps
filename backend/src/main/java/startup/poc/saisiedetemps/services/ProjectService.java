package startup.poc.saisiedetemps.services;

import java.util.List;

import startup.poc.saisiedetemps.dto.CreateProjectRequest;
import startup.poc.saisiedetemps.models.Project;

public interface ProjectService {

    public List<Project> findAllProjects();

    public Project createProject(CreateProjectRequest projectRequest);

    public List<Project>  findProjectsOfManager(Long managerId);
}