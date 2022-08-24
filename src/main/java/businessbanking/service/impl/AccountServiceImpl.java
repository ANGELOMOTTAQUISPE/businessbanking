package businessbanking.service.impl;

import businessbanking.model.Account;
import businessbanking.model.Fee;
import businessbanking.repo.IAccountRepo;
import businessbanking.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepo repo;

    public Mono<Account> register(Account obj) {
        Fee fee = new Fee();
        String AccountType=obj.getAccountType().toString();
        if (AccountType.equals("a")){
            fee.setMonthlyMovement(5);
            obj.setFee(fee);
        } else if (AccountType.equals("cc")) {
            fee.setMaintenanceCommission(200.0);
            obj.setFee(fee);
        } else if (AccountType.equals("pf")) {
            fee.setDate(LocalDateTime.now());
            fee.setMonthlyMovement(1);
            obj.setFee(fee);
        }
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
