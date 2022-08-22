package businessbanking.controller;

import businessbanking.model.Account;
import businessbanking.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private IAccountService service;
    @GetMapping
    public ResponseEntity<Flux<Account>> listar(){
        Flux<Account> lista = service.list();
        return new ResponseEntity<Flux<Account>>(lista, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Mono<Account>> registrar(@RequestBody Account checking){
        Mono<Account> p = service.register(checking);
        return new ResponseEntity<Mono<Account>>(p, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id) {
        return service.delete(id).map(r->ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping
    public ResponseEntity<Mono<Account>> update(@RequestBody Account account){
        Mono<Account> p = service.modify(account);
        return new ResponseEntity<Mono<Account>>(p, HttpStatus.OK);
    }
}
