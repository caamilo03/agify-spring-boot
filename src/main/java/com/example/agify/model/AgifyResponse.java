package com.example.agify.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgifyResponse {
    private String name;
    private Integer age;
    private Integer count;
}