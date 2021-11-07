package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String token;

}
