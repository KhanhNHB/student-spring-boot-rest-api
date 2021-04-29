package com.khanhnhb.springweb.builder;

import com.khanhnhb.springweb.entity.Student;
import com.khanhnhb.springweb.model.SearchStudentsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SearchStudentsSpecificationBuilder {

    public Specification<Student> buildSearchStudentsSpecification(SearchStudentsRequest request) {
        Specification<Student> specification = (root, query, cb) -> null;

        String query = request.getQuery();
        Long studentId;
        String studentName;
        Integer studentAge;

        if (query != null) {
            try {
                studentId = Long.parseLong(query);
                specification = handleSpecificationNullWithClauseOr(specification, StudentSpecificationBuilder.hasId(studentId));
            } catch (Exception e) {
                log.warn("Student ID is invalid with query {}", e.getMessage());
            }

            studentName = query;
            specification = handleSpecificationNullWithClauseOr(specification, StudentSpecificationBuilder.hasName(studentName));

            try {
                studentAge = Integer.parseInt(query);
                specification = handleSpecificationNullWithClauseOr(specification, StudentSpecificationBuilder.hasAge(studentAge));
            } catch (Exception e) {
                log.warn("Student Age is invalid with query {}", e.getMessage());
            }
        }

        studentId = request.getId();
        if (studentId != null) {
            specification = handleSpecificationNullWithClauseAnd(specification, StudentSpecificationBuilder.hasId(studentId));
        }

        studentName = request.getName();
        if (studentName != null) {
            specification = handleSpecificationNullWithClauseAnd(specification, StudentSpecificationBuilder.hasName(studentName));
        }

        studentAge = request.getAge();
        if (studentAge != null) {
            specification = handleSpecificationNullWithClauseAnd(specification, StudentSpecificationBuilder.hasAge(studentAge));
        }

        return specification;
    }

    private Specification<Student> handleSpecificationNullWithClauseAnd(Specification currentSpec, Specification newSpec) {
        if (currentSpec == null) {
            currentSpec = newSpec;
        } else {
            currentSpec = currentSpec.and(newSpec);
        }
        return currentSpec;
    }

    private Specification<Student> handleSpecificationNullWithClauseOr(Specification currentSpec, Specification newSpec) {
        if (currentSpec == null) {
            currentSpec = newSpec;
        } else {
            currentSpec = currentSpec.or(newSpec);
        }
        return currentSpec;
    }
}
