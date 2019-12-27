package com.springboot.personaVip.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.personaVip.document.Account;
import com.springboot.personaVip.document.PersonalVip;
import com.springboot.personaVip.dto.PersonalVipDto;


@Component
public class UtilConvert {

  private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	

  public PersonalVip convertPersonalVip(PersonalVipDto PersonalVipDto) {
	
    LOGGER.info("convertir ---> " + PersonalVipDto.toString());

    Account account = new Account();
    account.setIdAccount(PersonalVipDto.getIdAccount());
    account.setNumberAccount(PersonalVipDto.getNumberAccount());
    account.setNameAccount(PersonalVipDto.getNameAccount());
    
    
    PersonalVip PersonalVip = new PersonalVip();

    List<Account> listCuenta = new ArrayList<Account>();
    listCuenta.add(account);
    

    PersonalVip.setTipoDoc(PersonalVipDto.getTipoDoc());
    PersonalVip.setNumDoc(PersonalVipDto.getNumDoc());
    PersonalVip.setName(PersonalVipDto.getName()); 
    PersonalVip.setApePat(PersonalVipDto.getApePat());
    PersonalVip.setApeMat(PersonalVipDto.getApeMat());
    PersonalVip.setAddress(PersonalVipDto.getAddress());
    PersonalVip.setCreateDate(new Date());
    PersonalVip.setUpdateDate(new Date());
    PersonalVip.setListAccount(listCuenta);

    LOGGER.info("TRANSFORMADO ---> "+PersonalVip.toString());
    
    LOGGER.info("LISTA_CUENTAS ---> "+listCuenta.toString());
   
    return PersonalVip;
  }
  
  

}
