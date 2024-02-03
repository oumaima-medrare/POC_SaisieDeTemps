package startup.poc.saisiedetemps.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import startup.poc.saisiedetemps.auth.AuthenticationService;
import startup.poc.saisiedetemps.dto.TimeRequest;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.Time;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.repositories.ProjectRepository;
import startup.poc.saisiedetemps.repositories.TimeRepository;
import startup.poc.saisiedetemps.services.TimeService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TimeRepository timeRepository;

    public List<Time> findAllTimes() {
        return this.timeRepository.findAll();
    }


    public Optional<Time> findTimesById(Long id) {
        return this.timeRepository.findById(id);
    }

    @Transactional
    public Time createTime(TimeRequest timeRequest, User user) {
        Time time= new Time();

        time.setDateStart(timeRequest.getDateStart());
        time.setDateEnd(timeRequest.getDateEnd());
        time.setUser(user);

        Project project = this.projectRepository.findById(timeRequest.getProjectId()).orElse(null);
        time.setProject(project);
        System.out.println(time);
        System.out.println(user);
        System.out.println(project);
        return this.timeRepository.save(time);
    }

    public List<Time> findTimesOfUser(Long idUser) {
        return this.timeRepository.findByUserId(idUser);
    }

    @Transactional
    public boolean deleteTime(Long timeId) {
        this.timeRepository.deleteById(timeId);
        return true;
    }

    @Override
    public List<Time> findTimesOfUserAndProject(Long userId, Long projectId) {
        return timeRepository.findByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public List<Time> findTimesOfUserBetweenDatesAndProject(Long userId, LocalDateTime startDate, LocalDateTime endDate, Long projectId) {
        return timeRepository.findByUserIdAndDateStartBetweenAndProjectId(userId, startDate, endDate, projectId);
    }

    @Override
    public List<Time> findTimesOfUserBetweenDates(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return timeRepository.findByUserIdAndDateStartBetween(userId, startDate, endDate);
    }

    @Override
    public List<Time> findTimesByFilter(Long userId, LocalDateTime startDate, LocalDateTime endDate, Long projectId) {
        if (startDate != null && endDate != null && projectId != null) {
            return timeRepository.findByUserIdAndDateStartBetweenAndProjectId(userId, startDate, endDate, projectId);
        } else if (startDate != null && endDate != null) {
            return timeRepository.findByUserIdAndDateStartBetween(userId, startDate, endDate);
        } else if (projectId != null) {
            return timeRepository.findByUserIdAndProjectId(userId, projectId);
        } else {
            return timeRepository.findByUserId(userId);
        }
    }

    public Time updateTime(User currentUser,
                            Time time,
                            TimeRequest timeRequest){

        // Update the time entry with the provided data
        Optional<Project> project =  projectRepository.findById(timeRequest.getProjectId());
        time.setProject(project.get());
        time.setDateStart(timeRequest.getDateStart());
        time.setDateEnd(timeRequest.getDateEnd());

        timeRepository.save(time);
        return time;
    };

}
