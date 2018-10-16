package com.jewel.job.jobapplication.repository;

import com.jewel.job.jobapplication.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface ExampleRepository extends MongoRepository<Example, String> {
}