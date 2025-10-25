package com.campushub.dto;

import lombok.Data;

@Data
public class HubUserDto {
    private Integer id;
    private String name;
    private String email;
    private String profileImageId;
    private Integer age;
    private String password;
}