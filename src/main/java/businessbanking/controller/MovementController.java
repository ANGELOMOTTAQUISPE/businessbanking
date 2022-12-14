package businessbanking.controller;

import businessbanking.model.Movement;
import businessbanking.service.IMovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movement")
public class MovementController {
    private static final Logger logger = LoggerFactory.getLogger(MovementController.class);
    @Autowired
    private IMovementService service;
    @GetMapping
    public ResponseEntity<Flux<Movement>> list(){
        logger.info("Inicio metodo list() de MovementController");
        Flux<Movement> lista = null;
        try {
            lista = service.list();
        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo list() de MovementController");
        }
        return new ResponseEntity<Flux<Movement>>(lista, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Mono<Movement>> listId(@PathVariable("id") String id){
        logger.info("Inicio metodo listId() de requestcontroller");
        Mono<Movement> p = null;
        try {
            p = service.listofId(id);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo listId() de requestcontroller");
        }
        return new ResponseEntity<Mono<Movement>>(p, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Mono<Movement>> register(@RequestBody Movement movement){
        logger.info("Inicio metodo register() de MovementController");
        Mono<Movement> p = null;
        try {
            p = service.register(movement);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo register() de MovementController");
        }
        return new ResponseEntity<Mono<Movement>>(p, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        logger.info("Inicio metodo delete() de MovementController");
        return service.delete(id).map(r->ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping
    public ResponseEntity<Mono<Movement>> update(@RequestBody Movement movement){
        logger.info("Inicio metodo update() de MovementController");
        Mono<Movement> p = null;
        try {
            p = service.modify(movement);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo update() de MovementController");
        }
        return new ResponseEntity<Mono<Movement>>(p, HttpStatus.OK);
    }
    @GetMapping("/listMovementAccount/{accountNumber}")
    public ResponseEntity<Flux<Movement>> listmovementByAccount(@PathVariable("accountNumber") String accountNumber){
        logger.info("Inicio metodo listmovementByAccount() de MovementController");
        Flux<Movement> movement = service.listmovementByAccount(accountNumber);

        return new ResponseEntity<Flux<Movement>>(movement, HttpStatus.OK);
    }
    @GetMapping("/listMovementCredit/{creditNumber}")
    public ResponseEntity<Flux<Movement>> listmovementByCredit(@PathVariable("creditNumber") String creditNumber){
        logger.info("Inicio metodo listmovementByCredit() de MovementController");
        Flux<Movement> movement = service.listmovementByCredit(creditNumber);

        return new ResponseEntity<Flux<Movement>>(movement, HttpStatus.OK);
    }
}
