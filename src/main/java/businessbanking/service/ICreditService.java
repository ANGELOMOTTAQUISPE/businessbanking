package businessbanking.service;

import businessbanking.model.Client;
import businessbanking.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService extends ICRUD<Credit, String>{
    Flux<Credit> listCreditByDocumentNumberClient(String documentNumber);
    Mono<Long> countCreditByDocumentNumberClient(String documentNumber);
}
