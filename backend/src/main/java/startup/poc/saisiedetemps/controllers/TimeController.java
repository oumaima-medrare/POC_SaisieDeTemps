package startup.poc.saisiedetemps.controllers;

import com.lowagie.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import startup.poc.saisiedetemps.dto.TimeRequest;
import startup.poc.saisiedetemps.helpers.UserReportExporter;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.Time;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.repositories.ProjectRepository;
import startup.poc.saisiedetemps.services.TimeService;
import startup.poc.saisiedetemps.services.UserService;
import startup.poc.saisiedetemps.services.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name="Times")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
public class TimeController {
    @Autowired
    private TimeService timeService;
    @Autowired
    UserService userService;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/times")
    @Operation(summary = "Find all times")
    ResponseEntity<List<Time>> findAllTimes(
            @AuthenticationPrincipal(errorOnInvalidType = true) User currentUser,
            @RequestParam(required = false) String _startDate,
            @RequestParam(required = false) String _endDate,
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) Long userId) {

        LocalDateTime startDate = _startDate != null ? LocalDate.parse(_startDate, DateTimeFormatter.ISO_DATE).atStartOfDay() : null;
        LocalDateTime endDate = _endDate != null ? LocalDate.parse(_endDate, DateTimeFormatter.ISO_DATE).atStartOfDay().plusDays(1).minusSeconds(1) : null;

        List<Time> userTimes;
        if (userId != null) {
            // If userId is provided, use it for filtering
            userTimes = this.timeService.findTimesByFilter(userId, startDate, endDate, projectId);
        } else {
            // If userId is not provided, use the currentUser
            userTimes = this.timeService.findTimesByFilter(currentUser.getId(), startDate, endDate, projectId);
        }

        return new ResponseEntity<>(userTimes, HttpStatus.OK);
    }

    @PostMapping("/times")
    @Operation(summary = "Create a time")
    public ResponseEntity<Time> createTime(@RequestBody TimeRequest timeRequest, @AuthenticationPrincipal(errorOnInvalidType = true) User user) {
        return new ResponseEntity<Time>(this.timeService.createTime(timeRequest, user),HttpStatus.CREATED) ;
    }

    @DeleteMapping(value = "/times/{timeId}")
    @Operation(summary = "Delete a time")
    public ResponseEntity<Long> deleteTime(@PathVariable Long timeId) {

        boolean isRemoved = this.timeService.deleteTime(timeId);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(timeId, HttpStatus.OK);
    }

    @GetMapping("/times/current")
    @Operation(summary = "Find times by user")
    ResponseEntity<List<Time>> findCurrentUserTimes(@AuthenticationPrincipal(errorOnInvalidType = true) User user,
                                                    @RequestParam(required = false) String _startDate,
                                                    @RequestParam(required = false) String _endDate,
                                                    @RequestParam(required = false) Long projectId) {
        List<Time> userTimes;
        LocalDateTime startDate = LocalDate.parse(_startDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(_endDate, DateTimeFormatter.ISO_DATE).atStartOfDay().plusDays(1).minusSeconds(1);

        if (startDate != null && endDate != null && projectId != null) {
            userTimes = this.timeService.findTimesOfUserBetweenDatesAndProject(user.getId(), startDate, endDate, projectId);
        } else if (startDate != null && endDate != null) {
            userTimes = this.timeService.findTimesOfUserBetweenDates(user.getId(), startDate, endDate);
        } else if (projectId != null) {
            userTimes = this.timeService.findTimesOfUserAndProject(user.getId(), projectId);
        } else {
            userTimes = this.timeService.findTimesOfUser(user.getId());
        }

        return new ResponseEntity<>(userTimes, HttpStatus.OK);
    }


    @PutMapping("/{timeId}")
    @Operation(summary = "Update time entry")
    public ResponseEntity<Time> updateTimeEntry(
            @AuthenticationPrincipal(errorOnInvalidType = true) User currentUser,
            @PathVariable Long timeId,
            @RequestBody TimeRequest timeRequest) {
        // Check if the time entry belongs to the current user
        Optional<Time> existingTime = timeService.findTimesById(timeId);

        if (existingTime == null || !existingTime.get().getUser().getId().equals(currentUser.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Save the updated time entry
        Time updatedTime = timeService.updateTime(currentUser, existingTime.get(), timeRequest);
        return new ResponseEntity<>(updatedTime, HttpStatus.OK);
    }

    @GetMapping("/times/{userId}/date/export/pdf")
    public void exportToPDF(HttpServletResponse response, @PathVariable Long userId,
                            @RequestParam(required = false) String _startDate,
                            @RequestParam(required = false) String _endDate) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        LocalDateTime startDate = LocalDate.parse(_startDate, DateTimeFormatter.ISO_DATE).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(_endDate, DateTimeFormatter.ISO_DATE).atStartOfDay().plusDays(1).minusSeconds(1);

        List<Time> timesOfUser = timeService.findTimesOfUserBetweenDates(userId,startDate,endDate);


        User user = userService.findUser(userId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String date= String.valueOf(startDate.format(formatter));

        UserReportExporter exporter = new UserReportExporter(timesOfUser,user, date);
        exporter.export(response);

    }

}
