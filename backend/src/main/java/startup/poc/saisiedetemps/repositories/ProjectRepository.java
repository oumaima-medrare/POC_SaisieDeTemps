package startup.poc.saisiedetemps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import startup.poc.saisiedetemps.models.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByManagerUserId(Long managerId);

}