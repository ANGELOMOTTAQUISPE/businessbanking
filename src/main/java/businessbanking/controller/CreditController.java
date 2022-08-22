package businessbanking.controller;

import businessbanking.model.Credit;
import businessbanking.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit")
public class CreditController {
    @Autowired
    private ICreditService service;
    @GetMapping
    public ResponseEntity<Flux<Credit>> listar(){
        Flux<Credit> lista = service.list();
        return new ResponseEntity<Flux<Credit>>(lista, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Mono<Credit>> registrar(@RequestBody Credit credit){
        Mono<Credit> p = service.register(credit);
        return new ResponseEntity<Mono<Credit>>(p, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id) {
        return service.delete(id).map(r->ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping
    public ResponseEntity<Mono<Credit>> update(@RequestBody Credit credit){
        Mono<Credit> p = service.modify(credit);
        return new ResponseEntity<Mono<Credit>>(p, HttpStatus.OK);
    }
}
