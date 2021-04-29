package com.khanhnhb.springweb.common;

import lombok.Data;

@Data
public abstract class CommonResponse {
    private Integer code;
    private Boolean success;
}
