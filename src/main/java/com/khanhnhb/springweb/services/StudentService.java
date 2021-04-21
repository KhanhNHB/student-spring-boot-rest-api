package com.khanhnhb.springweb.services;

import com.khanhnhb.springweb.entities.Student;
import com.khanhnhb.springweb.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
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
}
