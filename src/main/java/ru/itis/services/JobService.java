package ru.itis.services;

import ru.itis.models.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    void createJob(Job j);
    void updateJob(Job j);
    void deleteJob(Job j);
    List<Job> getAllJobs();
    Optional<Job> getById(Long id);
    List<Job> getAllByActive(Boolean active);
    void createRelation(Long employeeId, Long employerId);
}
