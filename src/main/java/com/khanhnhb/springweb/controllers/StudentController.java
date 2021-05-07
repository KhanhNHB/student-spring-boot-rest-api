package com.khanhnhb.springweb.controllers;

import com.khanhnhb.springweb.common.CommonResponse;
import com.khanhnhb.springweb.entity.Student;
import com.khanhnhb.springweb.service.StudentService;
import com.khanhnhb.springweb.validation.StudentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.Valid;

@Controller
public class StudentController implements StudentControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @Autowired
    @Qualifier("studentValidator")
    Validator validator;

    @InitBinder(value = "student")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @Override
    public ResponseEntity<CommonResponse> getStudents(String attribute, String order, Integer offset, Integer limit) {
        return new ResponseEntity<>(studentService.getAllStudent(attribute, order, offset, limit), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> searchStudents(Long id, String name, Integer age, String q,
                                                         String attribute, String order, Integer offset, Integer limit) {
        return new ResponseEntity<>(studentService.searchStudents(id, name, age, q, attribute, order, offset, limit), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getStudent(Long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity create(@Valid Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity update(Long id, Student student) {
        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(Long id) {
        studentService.deleteStudent(id);
        return null;
    }
}
