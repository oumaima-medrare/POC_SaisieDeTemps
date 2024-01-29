package startup.poc.saisiedetemps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import startup.poc.saisiedetemps.dto.TimeRequest;
import startup.poc.saisiedetemps.models.Time;
import startup.poc.saisiedetemps.services.TimeService;
import startup.poc.saisiedetemps.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @Autowired
    private UserService userService;

    @GetMapping("/times")
    public ResponseEntity<List<Time>> findAllTimes(){
        return new ResponseEntity<List<Time>>(this.timeService.findAllTimes(), HttpStatus.OK);
    }

    @GetMapping("/times/{userId}")
    List<Time> findUserTimes(@PathVariable Long userId) {
        return this.timeService.findTimesOfUser(userId) ;
    }

    @PostMapping("/times")
    public ResponseEntity<Time> createTime(@RequestBody TimeRequest timeRequest) {
        return new ResponseEntity<Time>(this.timeService.createTime(timeRequest),HttpStatus.CREATED) ;
    }

    @DeleteMapping(value = "/times/{timeId}")
    public ResponseEntity<Long> deleteTime(@PathVariable Long timeId) {

        boolean isRemoved = this.timeService.deleteTime(timeId);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(timeId, HttpStatus.OK);
    }

    @GetMapping("/times/content/{userId}")
    public ResponseEntity<List<Time>> index(@PathVariable Long userId ) {
        return new ResponseEntity<List<Time>>(timeService.findTimesOfUser(userId),HttpStatus.OK);
    }


}
