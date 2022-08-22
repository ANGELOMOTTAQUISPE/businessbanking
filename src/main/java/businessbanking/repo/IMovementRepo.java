package businessbanking.repo;

import businessbanking.model.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovementRepo extends ReactiveMongoRepository<Movement, String> {
}
