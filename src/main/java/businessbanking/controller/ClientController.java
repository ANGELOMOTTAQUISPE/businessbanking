package businessbanking.controller;

import businessbanking.model.Client;
import businessbanking.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private IClientService service;
    @GetMapping
    public ResponseEntity<Flux<Client>> listar(){
        Flux<Client> lista = service.list();
        return new ResponseEntity<Flux<Client>>(lista, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Mono<Client>> registrar(@RequestBody Client client){
        Mono<Client> p = service.register(client);
        return new ResponseEntity<Mono<Client>>(p, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id) {
        return service.delete(id).map(r->ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<Mono<Client>> clientbydocumentNumber(@PathVariable("documentNumber") String documentNumber){
        Mono<Client> lista = service.clientbydocumentNumber(documentNumber);
        return new ResponseEntity<Mono<Client>>(lista, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Mono<Client>> update(@RequestBody Client client){
        Mono<Client> p = service.modify(client);
        return new ResponseEntity<Mono<Client>>(p, HttpStatus.OK);
    }
}
