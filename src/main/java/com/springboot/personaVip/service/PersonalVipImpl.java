package com.springboot.personaVip.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.personaVip.document.PersonalVip;
import com.springboot.personaVip.dto.PersonalVipDto;
import com.springboot.personaVip.repo.PersonalVipRepo;
import com.springboot.personaVip.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalVipImpl implements PersonalVipInterface {


  private static final Logger LOGGER = LoggerFactory.getLogger(PersonalVipImpl.class);

  @Autowired
  PersonalVipRepo repo;

  @Autowired
  UtilConvert convert;

  public Flux<PersonalVip> findAll() {

    return repo.findAll();
  }

  public Mono<PersonalVip> findById(String id) {

    return repo.findById(id);
  }

  public Mono<PersonalVip> save(PersonalVip personalVip) {

	  return repo.save(personalVip);
	 
  }

  public Mono<PersonalVip> update(PersonalVip personalVip,String id) {
	  
    return repo.findById(id).flatMap(p -> {

      
      p.setNumDoc(personalVip.getNumDoc());
      p.setName(personalVip.getName());
      p.setApePat(personalVip.getApePat());
      p.setApeMat(personalVip.getApeMat());
      p.setAddress(personalVip.getAddress());
      p.setUpdateDate(new Date());
    
      
      return repo.save(p);

    });
  }

  public Mono<Void> delete(PersonalVip personalVip) {
    return repo.delete(personalVip);
  }

  public Mono<PersonalVip> saveDto(PersonalVipDto personalVipDto) {

    return save(convert.convertPersonalVip(personalVipDto));
  }
//
//  @Override
//  public Mono<Personal> nameSearch(String name) {
//
//    return repo.findByName(name);
//  }
//
//  @Override
//  public Mono<Personal> findByNumDoc(String numDoc) {
//
//    return repo.findByNumDoc(numDoc);
//  }


}
