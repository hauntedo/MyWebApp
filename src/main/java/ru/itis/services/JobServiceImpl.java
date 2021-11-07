package ru.itis.services;

import ru.itis.models.Job;
import ru.itis.repositories.JobRepository;

import java.util.List;
import java.util.Optional;

public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void createJob(Job j) {
        jobRepository.save(j);
    }

    @Override
    public void updateJob(Job j) {
        jobRepository.update(j);
    }

    @Override
    public void deleteJob(Job j) {
        jobRepository.delete(j);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> getById(Long id) {
        return jobRepository.findById(id);
    }
}
