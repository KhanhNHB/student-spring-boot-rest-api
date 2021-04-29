package com.khanhnhb.springweb.model;

import com.khanhnhb.springweb.common.CommonResponse;
import com.khanhnhb.springweb.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class SearchStudentsResponse extends CommonResponse {
    private Long totalElements;
    private Integer numberOfElements;
    private Integer totalPages;
    private List<Student> studentList;
}
