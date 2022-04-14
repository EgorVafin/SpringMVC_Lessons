package app.controller;

import app.dao.TeacherRepository;
import app.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TeacherCreateController {

    private final TeacherRepository teacherRepository;

    public TeacherCreateController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teacher/create")
    public String index(Model model) {
        Person teacher = new Person();
        model.addAttribute("teacher", teacher);

        return "teacher/create";
    }

    @PostMapping("/teacher/create")
    public String createForm(@ModelAttribute @Valid Person teacher, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "teacher/create";
        } else {
            teacherRepository.save(teacher);

            return "redirect:/";
        }
    }
}
