package com.springboot.personaVip.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.personaVip.document.Account;
import com.springboot.personaVip.document.PersonalVip;
import com.springboot.personaVip.dto.PersonalVipDto;
import com.springboot.personaVip.service.PersonalVipInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/personalVip")
public class PersonalVipController {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonalVipController.class);


  @Autowired
  PersonalVipInterface service;


  @GetMapping
  public Mono<ResponseEntity<Flux<PersonalVip>>> toList() {

    return Mono.just(ResponseEntity.ok()
          .contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<PersonalVip>> search(@PathVariable String id) {

    return service.findById(id).map(p -> ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON).body(p))
      .defaultIfEmpty(ResponseEntity.notFound().build());

  }
  
  @PostMapping
  public Mono<ResponseEntity<PersonalVip>> save(@RequestBody PersonalVip personalVip) {

    return service.save(personalVip).map(p -> ResponseEntity.created(URI.create("/api/personal"))
                  .contentType(MediaType.APPLICATION_JSON).body(p));

  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<PersonalVip>> update(@RequestBody PersonalVipDto personalVipDto,
                    @PathVariable String id) {

    LOGGER.info("OBJETO RECIBIDO A ACTUALIZAR ---> " + personalVipDto.toString());
	
    return service.update(personalVipDto, id)
             .map(p -> ResponseEntity.created(URI.create("/api/personal".concat(p.getId())))
             .contentType(MediaType.APPLICATION_JSON).body(p))
             .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

    return service.findById(id).flatMap(p -> {
      return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

  }
  
  
  @PostMapping("/guardar")
  public Mono<ResponseEntity<PersonalVip>> save(@RequestBody PersonalVipDto personalVipDto) {

    LOGGER.info("PersonalDto RECIBIBIDO ---> " + personalVipDto.toString());
    
    return service.saveDto(personalVipDto).map(p -> ResponseEntity.created(URI.create("/api/personal"))
                  .contentType(MediaType.APPLICATION_JSON).body(p));

  }
  
  @GetMapping("/doc/{dni}")
  public Mono<ResponseEntity<PersonalVip>> searchDni(@PathVariable String dni) {

    return service.findByNumDoc(dni).map(p -> ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON).body(p))
      .defaultIfEmpty(ResponseEntity.notFound().build());

  }

  @GetMapping("/valid/{dni}")
  public Flux<Account> valid(@PathVariable String dni) {
   
    return service.findByNumDoc(dni).flatMapMany(personaVip ->{ 

    	return Flux.fromIterable(personaVip.getListAccount());
    		
    });	
    	
  }
    
    

}
