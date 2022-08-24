package businessbanking.repo;

import businessbanking.model.Account;
import businessbanking.model.Credit;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IAccountRepo extends ReactiveMongoRepository<Account, String> {
    @Query(value = "{'client.documentNumber' : ?0, accountType: ?1 }")
    Flux<Credit> findByAccountClient(String documentNumber, String accountType);
}
