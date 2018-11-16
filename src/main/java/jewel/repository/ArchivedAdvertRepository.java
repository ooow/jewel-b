package jewel.repository;

import jewel.repository.domain.ArchivedAdvert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedAdvertRepository extends MongoRepository<ArchivedAdvert, String> {
}
