package com.khanhnhb.springweb.builder;

import com.khanhnhb.springweb.entity.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecificationBuilder {

    public static Specification<Student> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Student> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Student> hasAge(Integer age) {
        return (root, query, cb) -> cb.equal(root.get("age"), age);
    }
}
