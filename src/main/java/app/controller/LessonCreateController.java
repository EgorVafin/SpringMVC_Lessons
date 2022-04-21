package app.controller;

import app.dao.LessonRepository;
import app.dao.PersonRepository;
import app.entity.Lesson;
import app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LessonCreateController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PersonRepository teacherRepository;

    @ModelAttribute
    private void persons(Model model) {
        List<Person> persons = teacherRepository.findAll();
        model.addAttribute("persons", persons);
    }

    @GetMapping("/lesson/create")
    public String index(Model model) {
        Lesson lesson = new Lesson();
        lesson.setDescription("test");
        model.addAttribute("lesson", lesson);

        return "lesson/create";
    }

    @PostMapping("/lesson/create")
    public String createForm(@ModelAttribute @Valid Lesson lesson, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "lesson/create";
        } else {
            lessonRepository.save(lesson);

            return "redirect:/";
        }
    }
}
