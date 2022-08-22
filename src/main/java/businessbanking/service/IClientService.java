package businessbanking.service;

import businessbanking.model.Client;
import reactor.core.publisher.Mono;

public interface IClientService extends ICRUD<Client, String>{
    Mono<Client> clientbydocumentNumber(String documentNumber);
}
