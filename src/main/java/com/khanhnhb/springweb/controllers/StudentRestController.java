package com.khanhnhb.springweb.controllers;

import com.khanhnhb.springweb.entities.Student;
import com.khanhnhb.springweb.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
public class StudentRestController {
    private static final Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getStudents() {
        logger.info("Get student list");
        return studentService.getAllStudent();
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable("id") long id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public Student createStudent(@RequestBody() Student student) {
        return studentService.createStudent(student);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public Student updateStudent(@PathVariable("id") long id,
                                 @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
    }
}
