package com.springboot.personaVip.service;

import com.springboot.personaVip.document.PersonalVip;
import com.springboot.personaVip.dto.PersonalVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalVipInterface {

	public Flux<PersonalVip> findAll();
	  
	  public Mono<PersonalVip> findById(String id);
	  
	  public Mono<PersonalVip> save(PersonalVip personalVip);
	  
	  public Mono<PersonalVip> update(PersonalVipDto personalVipDto,String id);
	  
	  public Mono<Void> delete(PersonalVip personalVip);
	  
	  public Mono<PersonalVip> saveDto(PersonalVipDto personalVipDto);
	  
	  public Mono<PersonalVip> nameSearch(String name);
	  
	  public Mono<PersonalVip> findByNumDoc(String numDoc);
	  
	  public Mono<PersonalVip> findAllAccount(String nameAccount);

}
