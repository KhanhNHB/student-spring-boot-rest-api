package com.khanhnhb.springweb.controllers;

import com.khanhnhb.springweb.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public interface StudentControllerApi {

    @GetMapping("")
    ResponseEntity getStudents(@RequestParam(value = "attribute", required = false) String attribute,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "offset", required = false) Integer offset,
                               @RequestParam(value = "limit", required = false) Integer limit);

    @GetMapping("/search")
    ResponseEntity searchStudents(@RequestParam(value = "id", required = false) Long id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "age", required = false) Integer age,
                                  @RequestParam(value = "q", required = false) String q,
                                  @RequestParam(value = "attribute", required = false) String attribute,
                                  @RequestParam(value = "order", required = false) String order,
                                  @RequestParam(value = "offset", required = false) Integer offset,
                                  @RequestParam(value = "limit", required = false) Integer limit);

    @GetMapping(value = "/{id}")
    ResponseEntity getStudent(@PathVariable("id") Long id);

    @PostMapping()
    ResponseEntity create(@Valid @RequestBody Student student);

    @PutMapping(value = "/{id}")
    ResponseEntity update(@PathVariable("id") Long id,
                          @RequestBody Student student);

    @DeleteMapping(value = "/{id}")
    ResponseEntity delete(@PathVariable("id") Long id);
}
