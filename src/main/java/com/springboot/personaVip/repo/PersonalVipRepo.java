package com.springboot.personaVip.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.personaVip.document.PersonalVip;

import reactor.core.publisher.Mono;

public interface PersonalVipRepo extends ReactiveMongoRepository<PersonalVip,String> {

  public Mono<PersonalVip> findByName(String name);

  @Query("{'nombre': ?0 }")
  public Mono<PersonalVip> nameSearch(String name);
  
  
  public Mono<PersonalVip> findByNumDoc(String numDoc);


  
}
