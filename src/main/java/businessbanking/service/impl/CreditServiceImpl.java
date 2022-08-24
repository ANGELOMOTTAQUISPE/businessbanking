package businessbanking.service.impl;

import businessbanking.controller.CreditController;
import businessbanking.model.Client;
import businessbanking.model.Credit;
import businessbanking.repo.IClientRepo;
import businessbanking.repo.ICreditRepo;
import businessbanking.service.ICreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CreditServiceImpl implements ICreditService {
    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
    @Autowired
    private ICreditRepo repo;
    @Autowired
    private IClientRepo repoClient;

    public Mono<Credit> register(Credit obj) {
        //Mono<Credit> p = service.register(credit);
        String documentNumber =obj.getClient().getDocumentNumber();
        return repoClient.findByDocumentNumber(documentNumber)
                .flatMap( cl -> {
                    if(cl.getClientType().equals("personal")){
                        logger.info("personal");
                        Flux<Credit> lista = repo.findByClient(documentNumber);
                        Mono<Long> count = lista.count();
                        return count
                                .flatMap( c->{
                                    //Mono<Long> cant = null;
                                    logger.info("-- : "+c);
                                    if(c>0){
                                        logger.info("1: "+c);
                                        throw new UserAlreadyPresentException(" El cliente ya tiene un credito: "+c);
                                        //return Mono.just("El cliente ya tiene un credito");
                                    }else{
                                        logger.info("2: "+c);
                                        return repo.save(obj);
                                    }
                                });
                    }else if(cl.getClientType().equals("empresarial")){
                        logger.info("empresarial: " + obj.getIdCredit() + " - " +  obj.getCreditCardNumber());
                        return repo.save(obj);
                    }else{
                        return Mono.just(obj);
                    }
                })
                .then(  Mono.just(obj) );
    }

    public Mono<Credit> modify(Credit obj) {
        return repo.save(obj);
    }

    public Flux<Credit> list() {
        return repo.findAll();
    }

    public Mono<Credit> listofId(String id) {
        Mono<Credit> op = repo.findById(id);
        return op;
    }
    public Mono<Credit> delete(String id) {
        return repo.findById(id).flatMap(r-> repo.delete(r).then(Mono.just(r)));
    }
    public Flux<Credit> listCreditByDocumentNumberClient(String documentNumber) {
        return repo.findByClient(documentNumber);
    }

    public Mono<Long> countCreditByDocumentNumberClient(String documentNumber) {
        return repo.CountByDocumentNumber(documentNumber);
    }


    class UserAlreadyPresentException extends RuntimeException {

        public UserAlreadyPresentException(String email) {
            super("User already present with email " + email);
        }
    }
}
