package businessbanking.repo;

import businessbanking.model.Client;
import businessbanking.model.Credit;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditRepo extends ReactiveMongoRepository<Credit, String> {
    //@Query("{'client.documentNumber':?0}")
    // Flux<Credit> findByDocumentNumber(String documentNumber);

    @Query(value = "{'client.documentNumber' : ?0 }")
    Flux<Credit> findByClient(String documentNumber);

    @Query(value = "{'client.documentNumber' : ?0 }")
    Mono<Long> CountByDocumentNumber(String documentNumber);

}
