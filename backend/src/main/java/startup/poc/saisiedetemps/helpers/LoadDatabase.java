package startup.poc.saisiedetemps.helpers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import startup.poc.saisiedetemps.models.Project;
import startup.poc.saisiedetemps.models.Time;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.repositories.ProjectRepository;
import startup.poc.saisiedetemps.repositories.TimeRepository;
import startup.poc.saisiedetemps.repositories.UserRepository;
import startup.poc.saisiedetemps.securtiy.Role;

import java.time.LocalDateTime;



@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initData(ProjectRepository projectRepository,
                               UserRepository userRepository, TimeRepository timeRepository) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {

            }
        };
    }

    private void initProjectTable(ProjectRepository projectRepository) {
        Project prj1 = new Project();
        prj1.setTitle("Project Start-up POC");
        prj1.setDescription("Fise 3 Springboot Angular project");
        projectRepository.save(prj1);
    }

    private void initTimeTable(TimeRepository timeRepository) {
        Time time = new Time();
        time.setDateStart(LocalDateTime.now());
        time.setDateEnd(LocalDateTime.now());
        timeRepository.save(time);
    }

    private void initUserTable(UserRepository userRepository) {
        User user = new User();
        user.setUsername("usernameOne");
        user.setPassword("1234567");
        user.setEmail("username@gmail.com");
        user.setFirstname("firstname");
        user.setLastname("lastname");
        user.setRole(Role.valueOf(String.valueOf(Role.ADMIN)));
        userRepository.save(user);
    }


}