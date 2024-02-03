package startup.poc.saisiedetemps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


import startup.poc.saisiedetemps.models.Time;


public interface TimeRepository extends JpaRepository<Time, Long> {
    public List<Time> findByUserId(Long managerId);

    List<Time> findByUserIdAndDateStartBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    List<Time> findByUserIdAndProjectId(Long userId, Long projectId);

    List<Time> findByUserIdAndDateStartBetweenAndProjectId(Long userId, LocalDateTime startDate, LocalDateTime endDate, Long projectId);

}