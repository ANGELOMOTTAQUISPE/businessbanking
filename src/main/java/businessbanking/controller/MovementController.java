package businessbanking.controller;

import businessbanking.model.Movement;
import businessbanking.service.IMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movement")
public class MovementController {
    @Autowired
    private IMovementService service;
    @GetMapping
    public ResponseEntity<Flux<Movement>> listar(){
        Flux<Movement> lista = service.list();
        return new ResponseEntity<Flux<Movement>>(lista, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Mono<Movement>> registrar(@RequestBody Movement movement){
        Mono<Movement> p = service.register(movement);
        return new ResponseEntity<Mono<Movement>>(p, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id) {
        return service.delete(id).map(r->ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping
    public ResponseEntity<Mono<Movement>> update(@RequestBody Movement movement){
        Mono<Movement> p = service.modify(movement);
        return new ResponseEntity<Mono<Movement>>(p, HttpStatus.OK);
    }
}
