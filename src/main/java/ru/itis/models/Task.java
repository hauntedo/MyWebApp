package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Task {

    private Long id;
    private String title;
    private String description;

}
