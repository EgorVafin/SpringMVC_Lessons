package app.controller.registration;

import app.dao.PersonRepository;
import app.entity.Lesson;
import app.entity.Person;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {


    private final RegistrationDtoValidator validator;

    private final PasswordEncoder passwordEncoder;

    private final PersonRepository personRepository;

    @GetMapping("/register")
    public String index(Model model) {

        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("person", registrationDto);

        return "register";
    }

    @PostMapping("/register")
    public String createForm(@ModelAttribute(name = "person") @Valid RegistrationDto registrationDto, BindingResult bindingResult) {

        validator.validate(registrationDto, bindingResult);

        if (bindingResult.hasErrors()) {

            return "register";
        } else {

            Person person = new Person();

            person.setName(registrationDto.getName());
            person.setSurname(registrationDto.getSurname());
            person.setEmail(registrationDto.getEmail());
            person.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

            personRepository.save(person);

            return "redirect:/login";
        }
    }
}
