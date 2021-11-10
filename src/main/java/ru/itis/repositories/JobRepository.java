package ru.itis.repositories;

import ru.itis.models.Job;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends CrudRepository<Job> {

    List<Job> findAllByActive(Boolean active);
    void saveRelation(Long employeeId, Long employerId);
}
