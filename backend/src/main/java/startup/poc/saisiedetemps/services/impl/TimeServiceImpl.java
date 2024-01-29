package startup.poc.saisiedetemps.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startup.poc.saisiedetemps.auth.AuthenticationService;
import startup.poc.saisiedetemps.dto.TimeRequest;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.Time;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.repositories.ProjectRepository;
import startup.poc.saisiedetemps.repositories.TimeRepository;
import startup.poc.saisiedetemps.services.TimeService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private AuthenticationService authService;


    public List<Time> findAllTimes() {
        return this.timeRepository.findAll();
    }


    public Optional<Time> findTimesById(Long id) {
        return this.timeRepository.findById(id);
    }

    @Transactional
    public Time createTime(TimeRequest timeRequest) {
        Time time= new Time();

        time.setDateStart(timeRequest.getDateStart());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        time.setDateOfProject(String.valueOf(time.getDateStart().format(formatter)));
        time.setDateEnd(timeRequest.getDateEnd());
        User currentUser = this.authService.getLoggedInUserInfo(timeRequest.getUsername());
        time.setUser(currentUser);

        Project project = this.projectRepository.findById(timeRequest.getProjectId()).orElse(null);
        time.setProject(project);

        return this.timeRepository.save(time);
    }

    public List<Time> getTimeContent(Long userId,String date) {
        return timeRepository.findAllByUserUserIdAndDateOfProject(userId, date);
    }

    public List<Time> findTimesOfUser(Long idUser) {
        return this.timeRepository.findByUserUserId(idUser);
    }


    @Transactional
    public boolean deleteTime(Long timeId) {
        this.timeRepository.deleteById(timeId);
        return true;
    }
}
