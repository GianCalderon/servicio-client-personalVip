package com.springboot.personaVip.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.personaVip.document.Account;
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

  public Mono<PersonalVip> save(PersonalVip PersonalVip) {

	  PersonalVip.setCreateDate(new Date());
	  PersonalVip.setUpdateDate(new Date());
	  PersonalVip.setListAccount(new ArrayList<Account>());
	
      return repo.save(PersonalVip);
  }

  public Mono<PersonalVip> update(PersonalVipDto PersonalVipDto,String id) {

    return repo.findByNumDoc(id).flatMap(persona -> {
  	
      List<Account> list = persona.getListAccount();
      
      Account account = new Account();
      
      account.setIdAccount(PersonalVipDto.getIdAccount());
      account.setNumberAccount(PersonalVipDto.getNumberAccount());
      account.setNameAccount(PersonalVipDto.getNameAccount());

      list.add(account);

      persona.setTipoDoc(PersonalVipDto.getTipoDoc());
      persona.setNumDoc(PersonalVipDto.getNumDoc());
      persona.setName(PersonalVipDto.getName());
      persona.setApePat(PersonalVipDto.getApePat());
      persona.setApeMat(PersonalVipDto.getApeMat());
      persona.setAddress(PersonalVipDto.getAddress());
      persona.setUpdateDate(new Date());
      persona.setListAccount(list);
      
      return repo.save(persona);
  
    });
  }

  public Mono<Void> delete(PersonalVip PersonalVip) {
    return repo.delete(PersonalVip);
  }

  public Mono<PersonalVip> saveDto(PersonalVipDto PersonalVipDto) {

    return repo.save(convert.convertPersonalVip(PersonalVipDto));
  }

  @Override
  public Mono<PersonalVip> nameSearch(String name) {

    return repo.findByName(name);
  }

  @Override
  public Mono<PersonalVip> findByNumDoc(String numDoc) {

    return repo.findByNumDoc(numDoc);
  }
  
  @Override
  public Mono<PersonalVip> findAllAccount(String nameAccount) {

    return repo.searchAccount(nameAccount);
  }
  

}
