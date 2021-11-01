package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Boolean sex;
    private String email;
    private String password;

}
