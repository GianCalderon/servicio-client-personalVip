package com.springboot.personaVip.service;

import com.springboot.personaVip.document.PersonalVip;
import com.springboot.personaVip.dto.PersonalVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalVipInterface {

  public Flux<PersonalVip> findAll();
  
  public Mono<PersonalVip> findById(String id);
  
  public Mono<PersonalVip> save(PersonalVip personalVip);
  
  public Mono<PersonalVip> update(PersonalVip personalVip,String id);
  
  public Mono<Void> delete(PersonalVip personalVip);
  
  public Mono<PersonalVip> saveDto(PersonalVipDto personalVipDto);
  
//  public Mono<Personal> saveDto(PersonalDto personalDto);
//  
//  public Mono<Personal> nameSearch(String name);
//  
//  public Mono<Personal> findByNumDoc(String numDoc);


}
