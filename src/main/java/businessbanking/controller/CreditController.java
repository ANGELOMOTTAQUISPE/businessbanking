package businessbanking.controller;

import businessbanking.exception.ModelNotFoundException;
import businessbanking.model.Account;
import businessbanking.model.Client;
import businessbanking.model.Credit;
import businessbanking.service.IClientService;
import businessbanking.service.ICreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit")
public class CreditController {
    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
    @Autowired
    private ICreditService service;
    @Autowired
    private IClientService clientService;
    @GetMapping
    public ResponseEntity<Flux<Credit>> list(){
        logger.info("Inicio metodo list() de CreditController");
        Flux<Credit> lista = null;
        String documentNumberClient ="75399757";
        Flux<Credit> credito =service.list().filter(a->a.equals(a.getClient().getDocumentNumber().equals(documentNumberClient)));
        credito.subscribe(System.out::println);
        try {
            lista = service.list();

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo list() de CreditController");
        }
        return new ResponseEntity<Flux<Credit>>(lista, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Mono<Credit>> register(@RequestBody Credit credit){

        logger.info("Inicio metodo register() de CreditController");

        Mono<Credit> p = service.register(credit);

       /*clientService.clientbydocumentNumber(documentNumber)
               .flatMap(cl ->{
                   if(cl.getClientType().equals("personal")){
                       logger.info("personal");
                       Flux<Credit> lista = service.listCreditByDocumentNumberClient(documentNumber);
                       Mono<Long> count=lista.count();
                       logger.info("count: "+count);
                       Mono<Credit> credito= null;
                       count.subscribe(c->{
                           if(c>0){
                               logger.info("1: "+count);
                               //service.register(credit);
                           }else{
                               logger.info("2: "+count);
                                return service.register(credit);
                           }
                       });
                       //return cl;
                   }else if(cl.getClientType().equals("empresarial")){
                       logger.info("empresarial: " + credit.getIdCredit() + " - " +  credit.getCreditCardNumber());
                       service.register(credit).log();
                       //return cl;
                   }
                   return service.register(credit);
                   //return clientService.save(oldBook);
               })
               .map(updateBook -> new ResponseEntity<> (updateBook, HttpStatus.OK) )
               .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK) );
                //.handle((docuemnt, sink) -> sink.error(new ModelNotFoundException("...")));

        Mono<Credit> p = null;*/

        /*client.subscribe(cl->{
            if(cl.getClientType().equals("personal")){
                logger.info("personal");
                Flux<Credit> lista = service.listCreditByDocumentNumberClient(documentNumber);
                Mono<Long> count=lista.count();
                logger.info("count: "+count);
                count.subscribe(c->{
                    if(c>0){
                        logger.info("1: "+count);
                    }else{
                        logger.info("2: "+count);
                        Mono<Credit> cd = service.register(credit);
                        p.just(cd);
                    }
                });
            }else if(cl.getClientType().equals("empresarial")){
                logger.info("empresarial: " + credit.getIdCredit() + " - " +  credit.getCreditCardNumber());
                Mono<Credit> cd = service.register(credit);
                p.just(cd);
            }
        });*/

        // p.just(credit);
        return new ResponseEntity<Mono<Credit>>(p, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        logger.info("Inicio metodo delete() de CreditController");
        return service.delete(id).map(r->ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping
    public ResponseEntity<Mono<Credit>> update(@RequestBody Credit credit){
        logger.info("Inicio metodo update() de CreditController");
        Mono<Credit> p = null;
        try {
            p = service.modify(credit);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo update() de CreditController");
        }
        return new ResponseEntity<Mono<Credit>>(p, HttpStatus.OK);
    }

    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<Mono<Client>> listCreditByDocumentNumberClient(@PathVariable("documentNumber") String documentNumber){
        logger.info("Inicio metodo listCreditByDocumentNumberClient() de CreditController");
        Mono<Client> client = clientService.clientbydocumentNumber(documentNumber);





        /*String documentNumberClient ="75399757";
        Flux<Credit> credito =service.list().filter(a->a.equals(a.getClient().getDocumentNumber().equals(documentNumberClient)));
        credito.subscribe(System.out::println);
        try {
            lista = service.list();

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo list() de CreditController");
        }*/
        return new ResponseEntity<Mono<Client>>(client, HttpStatus.OK);
    }

}
