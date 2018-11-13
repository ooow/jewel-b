package jewel.repository;

import jewel.domain.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends MongoRepository<Ad, String> {
}