package startup.poc.saisiedetemps.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import startup.poc.saisiedetemps.dto.TimeRequest;
import startup.poc.saisiedetemps.models.Time;

public interface TimeService {


    public List<Time> findAllTimes();


    public Optional<Time> findTimesById(Long id);


    public Time createTime(TimeRequest timeRequest);


    public List<Time> getTimeContent(Long userId,String date);


    public List<Time> findTimesOfUser(Long idUser);


    public boolean deleteTime(Long timeId);

}
