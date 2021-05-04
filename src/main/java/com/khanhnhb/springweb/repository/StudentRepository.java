package com.khanhnhb.springweb.repository;

import com.khanhnhb.springweb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query(value = "SELECT id, age, phone, name, email FROM student WHERE upper(name) = upper(?1)", nativeQuery = true)
    Optional<Student> findByName(String name);
}