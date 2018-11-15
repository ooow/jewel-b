package jewel.repository;

import jewel.domain.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends MongoRepository<Advert, String> {

    Page<Advert> findAll(Pageable pageable);

    List<Advert> findAllByOrderByCreatedAtDesc();
}
