package com.khanhnhb.springweb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Max(value = 80, message = "Age must be lower then 80")
    @Min(value = 18, message = "Age must be greater than 18")
    private int age;
    private String phone;
    private String name;
    private String email;

}
