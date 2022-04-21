package app.controller;

import app.dao.LessonRepository;
import app.dao.PersonRepository;
import app.entity.Lesson;
import app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LessonListController {

    private final LessonRepository lessonRepository;
    private final PersonRepository teacherRepository;

    public LessonListController(@Autowired LessonRepository lessonRepository,
                                @Autowired PersonRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Lesson> lessons = lessonRepository.findAll();
        List<Person> teachers = teacherRepository.findAll();
        model.addAttribute("lessons", lessons);
        model.addAttribute("teachers", teachers);
        return "index";
    }
}
