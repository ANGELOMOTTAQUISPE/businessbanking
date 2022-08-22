package businessbanking.repo;

import businessbanking.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditRepo extends ReactiveMongoRepository<Credit, String> {
}
