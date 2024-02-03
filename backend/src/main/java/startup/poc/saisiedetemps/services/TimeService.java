package startup.poc.saisiedetemps.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import startup.poc.saisiedetemps.dto.TimeRequest;
import startup.poc.saisiedetemps.models.Time;
import startup.poc.saisiedetemps.models.User;

public interface TimeService {

    public List<Time> findAllTimes();

    public Optional<Time> findTimesById(Long id);

    public Time createTime(TimeRequest timeRequest, User user);

    public List<Time> findTimesOfUser(Long idUser);

    public boolean deleteTime(Long timeId);

    public List<Time> findTimesOfUserBetweenDates(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    public List<Time> findTimesOfUserAndProject(Long userId, Long projectId);

    public List<Time> findTimesOfUserBetweenDatesAndProject(Long userId, LocalDateTime startDate, LocalDateTime endDate, Long projectId);
    public List<Time> findTimesByFilter(Long userId, LocalDateTime startDate, LocalDateTime endDate, Long projectId);

    public Time updateTime(User currentUser, Time time, TimeRequest timeRequest);



}
