package ru.itis.models;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {

    private Long id;
    private String title;
    private String description;
    private Long employerId;
    private boolean active;
}
