package app.controller;

import app.dao.LessonRepository;
import app.dao.PersonRepository;
import app.entity.Lesson;
import app.entity.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LessonUpdateController {

    private final LessonRepository lessonRepository;

    public LessonUpdateController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Autowired
    private PersonRepository teacherRepository;

    @ModelAttribute
    private void lessons(Model model) {
        List<Person> persons = teacherRepository.findAll();
        model.addAttribute("persons", persons);
    }

    @GetMapping("/lesson/{id}/edit")
    public String showForm(@PathVariable long id, Model model) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        model.addAttribute("lesson", lesson);
        if (lesson.isPresent()) {
            System.out.println("lesson update page GET");

            return "lesson/create";
        } else {

            return "redirect:/";
        }
    }


    @PostMapping("/lesson/{id}/edit")
    public String updateLesson(@PathVariable(name = "id") Lesson entity, @ModelAttribute @Valid Lesson lesson, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "lesson/create";
        } else {

            BeanUtils.copyProperties(lesson, entity);
            lessonRepository.save(entity);

            return "redirect:/";
        }
    }
}
