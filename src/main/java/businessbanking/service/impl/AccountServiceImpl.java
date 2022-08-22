package businessbanking.service.impl;

import businessbanking.model.Account;
import businessbanking.repo.IAccountRepo;
import businessbanking.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepo repo;

    public Mono<Account> register(Account obj) {
        return repo.save(obj);
    }

    public Mono<Account> modify(Account obj) {
        return repo.save(obj);
    }

    public Flux<Account> list() {
        return repo.findAll();
    }

    public Mono<Account> listofId(String id) {
        Mono<Account> op = repo.findById(id);
        return op;
    }
    public Mono<Account> delete(String id) {
        return repo.findById(id).flatMap(r-> repo.delete(r).then(Mono.just(r)));
    }
}
