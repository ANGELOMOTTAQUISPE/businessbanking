package businessbanking.service.impl;

import businessbanking.model.Credit;
import businessbanking.repo.ICreditRepo;
import businessbanking.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CreditServiceImpl implements ICreditService {
    @Autowired
    private ICreditRepo repo;
    public Mono<Credit> register(Credit obj) {
        return repo.save(obj);
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
}
