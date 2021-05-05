package com.khanhnhb.springweb.validator;

import com.khanhnhb.springweb.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

@Component
public class StudentValidator implements Validator {

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;

        ValidationUtils.rejectIfEmpty(errors, "name",
                messageSource.getMessage("student.name.error", null, Locale.getDefault()));

        if (student.getPhone().trim().length() < 3 ||
                student.getPhone().trim().length() > 12) {
            errors.rejectValue("phone",
                    messageSource.getMessage("student.phone.error", null, Locale.getDefault()),
                    "Student phone should be greater than zero and lower than twelve");
        }
    }
}
