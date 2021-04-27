package com.khanhnhb.springweb.controllers;

import com.khanhnhb.springweb.entities.Student;
import com.khanhnhb.springweb.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentRestController {
    private static final Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> getStudents() {
        logger.info("Get student list");
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.OK);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Student> createStudent(@RequestBody() Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id,
                                 @RequestBody Student student) {
        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
    }

    @DeleteMapping(value = "/students/{id}")
    public void deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
    }
}
