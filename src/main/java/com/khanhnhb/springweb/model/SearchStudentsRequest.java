package com.khanhnhb.springweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchStudentsRequest {
    public static final Integer DEFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 5;
    public static final Integer MAX_PAGE_SIZE = 10;

    private Long id;
    private String name;
    private Integer age;
    private String query;
    private String attribute;
    private String order;
    private Integer offset;
    private Integer limit;
    private Sort sort;
}
