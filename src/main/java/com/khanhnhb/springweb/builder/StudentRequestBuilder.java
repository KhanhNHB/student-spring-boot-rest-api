package com.khanhnhb.springweb.builder;

import com.khanhnhb.springweb.model.SearchStudentsRequest;
import com.khanhnhb.springweb.util.SortUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class StudentRequestBuilder {

    public SearchStudentsRequest buildGetStudentsRequest(String attribute, String order, Integer offset, Integer limit) {
        SearchStudentsRequest request = new SearchStudentsRequest();

        if (offset != null) {
            request.setOffset(offset);
        } else {
            request.setOffset(SearchStudentsRequest.DEFAULT_PAGE_NUMBER);
        }

        if (limit != null) {
            request.setLimit(limit);
        } else {
            request.setLimit(SearchStudentsRequest.DEFAULT_PAGE_SIZE);
        }

        Sort sort = SortUtil.buildSort(attribute, order);
        request.setSort(sort);
        return request;
    }

    public SearchStudentsRequest buildSearchStudentsRequest(Long id, String name, Integer age,
                                                            String query, String attribute, String order,
                                                            Integer offset, Integer limit) {
        SearchStudentsRequest request = new SearchStudentsRequest();

        if (id != null && id > 0) {
            request.setId(id);
        }

        if (name != null && !name.trim().isBlank()) {
            request.setName(name);
        }

        if (age != null && age > 0) {
            request.setAge(age);
        }

        if (query != null && !query.trim().isBlank()) {
            request.setQuery(query);
        }

        if (offset != null) {
            request.setOffset(offset);
        } else {
            request.setOffset(SearchStudentsRequest.DEFAULT_PAGE_NUMBER);
        }

        if (limit != null) {
            request.setLimit(limit);
        } else {
            request.setLimit(SearchStudentsRequest.DEFAULT_PAGE_SIZE);
        }

        Sort sort = SortUtil.buildSort(attribute, order);

        request.setSort(sort);
        return request;
    }
}
