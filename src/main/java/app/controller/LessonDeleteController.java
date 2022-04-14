package app.controller;

import app.dao.LessonRepository;
import app.entity.Lesson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class LessonDeleteController {

    private final LessonRepository lessonRepository;

    public LessonDeleteController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/lesson/{id}/delete")
    public String showForm(@PathVariable long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson.isPresent()) {
            lessonRepository.deleteById(id);

            return "redirect:/";
        } else {

            return "redirect:/";
        }
    }
}
