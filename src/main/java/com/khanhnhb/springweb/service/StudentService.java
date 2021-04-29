package com.khanhnhb.springweb.service;

import com.khanhnhb.springweb.builder.StudentRequestBuilder;
import com.khanhnhb.springweb.builder.SearchStudentsSpecificationBuilder;
import com.khanhnhb.springweb.common.CommonResponse;
import com.khanhnhb.springweb.entity.Student;
import com.khanhnhb.springweb.model.SearchStudentsRequest;
import com.khanhnhb.springweb.model.SearchStudentsResponse;
import com.khanhnhb.springweb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentRequestBuilder studentRequestBuilder;

    @Autowired
    SearchStudentsSpecificationBuilder studentSpecificationBuilder;

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CommonResponse getAllStudent(String attribute, String order, Integer offset, Integer limit) {
        SearchStudentsRequest request = studentRequestBuilder.buildGetStudentsRequest(attribute, order, offset, limit);

        PageRequest pageRequest = PageRequest.of(request.getOffset(), request.getLimit(), request.getSort());
        Page<Student> studentPage = studentRepository.findAll(pageRequest);

        SearchStudentsResponse response = new SearchStudentsResponse();
        response.setCode(200);
        response.setSuccess(true);
        response.setTotalElements(studentPage.getTotalElements());
        response.setNumberOfElements(studentPage.getNumberOfElements());
        response.setTotalPages(studentPage.getTotalPages());
        response.setStudentList(studentPage.getContent());

        return response;
    }

    public Student getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            // TODO: Throw exception not found student by id
            return null;
        }

        return student.get();
    }

    public Student createStudent(Student student) {
        // TODO: Check existed
        Student createdStudent = studentRepository.save(student);
        return this.getStudent(createdStudent.getId());
    }

    public Student updateStudent(long id, Student updateStudent) {
        // TODO: Check existed
        Student student = this.getStudent(id);
        // TODO: Mapper Handle
        student.setPhone(updateStudent.getPhone());
        Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }

    public void deleteStudent(long id) {
        Student student = this.getStudent(id);

        if (student == null) {
            // TODO: Throw exception
            return;
        }

        studentRepository.deleteById(student.getId());
    }

    public CommonResponse searchStudents(Long id, String name, Integer age, String q,
                                 String attribute, String order, Integer offset, Integer limit) {
        SearchStudentsRequest request = studentRequestBuilder.buildSearchStudentsRequest(id, name, age, q, attribute, order, offset, limit);

        Specification<Student> specification = studentSpecificationBuilder.buildSearchStudentsSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getOffset(), request.getLimit(), request.getSort());
        Page<Student> studentPage = studentRepository.findAll(specification, pageRequest);

        SearchStudentsResponse response = new SearchStudentsResponse();
        response.setCode(200);
        response.setSuccess(true);
        response.setTotalElements(studentPage.getTotalElements());
        response.setNumberOfElements(studentPage.getNumberOfElements());
        response.setTotalPages(studentPage.getTotalPages());
        response.setStudentList(studentPage.getContent());

        return response;
    }
}
