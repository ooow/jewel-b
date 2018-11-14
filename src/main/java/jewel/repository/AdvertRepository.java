package jewel.repository;

import jewel.domain.Advert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends MongoRepository<Advert, String> {
}
