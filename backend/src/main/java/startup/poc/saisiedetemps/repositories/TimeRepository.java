package startup.poc.saisiedetemps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import startup.poc.saisiedetemps.models.Time;


public interface TimeRepository extends JpaRepository<Time, Long> {


    public List<Time> findAllByUserIdAndDateOfProject(Long userId, String date);

    public List<Time> findByUserId(Long managerId);


}