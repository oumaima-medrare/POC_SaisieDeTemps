package startup.poc.saisiedetemps.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private String dateOfProject;
    // many to one vers users
    @ManyToOne
    private User user;

    // many to one vers projet
    @ManyToOne
    private Project project;

}