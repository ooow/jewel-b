package com.jewel.job.jobapplication.repository;

import com.jewel.job.jobapplication.domain.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends MongoRepository<Ad, String> {
}
