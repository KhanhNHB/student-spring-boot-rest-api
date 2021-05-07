package com.khanhnhb.springweb.validation;

import com.khanhnhb.springweb.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class StudentValidator implements Validator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "\\d";

    @Autowired
    MessageSource messageSource;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
//
//        ValidationUtils.rejectIfEmpty(errors, "name",
//                messageSource.getMessage("student.name.error", null, Locale.getDefault()));

        String email = student.getEmail();
        String phone = student.getPhone();
        if (email != null && !email.isBlank()) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            if (!pattern.matcher(email).find()) {
                errors.rejectValue("email", messageSource.getMessage(
                        "student.email.error",
                        null,
                        Locale.getDefault())
                );
            }
        } else {
            if (phone != null && !phone.isBlank()) {
                String prefix = phone.substring(0, 3);
                String phoneNumber = phone.substring(3);

                Pattern pattern = Pattern.compile(PHONE_REGEX);
                if (!prefix.equals("+84")
                        || !pattern.matcher(phoneNumber).find()
                        || phoneNumber.length() != 9) {
                    errors.rejectValue("phone", messageSource.getMessage(
                            "student.phone.error",
                            null,
                            Locale.getDefault())
                    );
                }
            }
        }
    }
}
