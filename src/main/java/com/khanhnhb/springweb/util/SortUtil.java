package com.khanhnhb.springweb.util;

import org.springframework.data.domain.Sort;

public class SortUtil {

    public static Sort buildSort(String attribute, String order) {
        Sort sort = Sort.unsorted();

        if (attribute == null || attribute.trim().isBlank()) {
            return sort;
        }

        if (order == null && order.trim().isBlank()) {
            return sort;
        }

        sort = Sort.by(attribute);
        switch (order.toLowerCase()) {
            case "asc":
                sort = sort.ascending();
                break;
            case "desc":
                sort = sort.descending();
                break;
            default:
                break;
        }
        return sort;
    }
}
