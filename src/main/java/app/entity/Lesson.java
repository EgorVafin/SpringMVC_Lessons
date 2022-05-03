package app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "lesson_date")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;


    @Column(name = "duration")
    @Min(value = 30, message = "lesson must be min 30 min")
    @Max(value = 180, message = "lesson must be max 180 min")
    private int duration;

    @Column(name = "lesson_desc", nullable = true)
    @NotBlank
    private String description;

    @Column(name = "home_work", nullable = true)
    @NotBlank(message = "homeWork cannot be empty")
    private String homeWork;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Person teacher;

    @Column(name="comment", nullable = true)
    private String comment;

    @ManyToMany
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "lesson_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private List<Person> students;

}
