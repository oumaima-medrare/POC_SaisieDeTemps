package startup.poc.saisiedetemps.services;

import java.util.List;

import startup.poc.saisiedetemps.models.Project;

public interface ProjectService {

    public List<Project> findAllProjects();

    public Project createProject(Project project,String username);

    public List<Project>  findProjectsOfManager(Long managerId);
}