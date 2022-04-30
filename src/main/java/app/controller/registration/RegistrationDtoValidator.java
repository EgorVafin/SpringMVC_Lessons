package app.controller.registration;

import app.dao.PersonRepository;
import app.entity.Person;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegistrationDtoValidator implements Validator {

    private final PersonRepository personRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

         RegistrationDto dto = (RegistrationDto) o;

         if(!personRepository.findByEmail(dto.getEmail()).isEmpty()) {
             errors.rejectValue("email", "RegistrationDto.email");
        }

         if(!dto.getPassword().equals(dto.getConfirmPassword())) {
             errors.rejectValue("confirmPassword", "RegistrationDto.passwordConfirm");
         }
    }
}
