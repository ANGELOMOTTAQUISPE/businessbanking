package businessbanking.repo;

import businessbanking.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepo extends ReactiveMongoRepository<Account, String> {
}
