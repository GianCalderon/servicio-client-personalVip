package com.springboot.personaVip.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.springboot.personaVip.document.PersonalVip;
import com.springboot.personaVip.dto.PersonalVipDto;

@Component
public class UtilConvert {

  public PersonalVip convertPersonalVip(PersonalVipDto personalVipDto) {

    Map<String,String> cuentaMap = new HashMap<String,String>();
    PersonalVip personalVip = new PersonalVip();

    cuentaMap.put(personalVipDto.getNameAccount(), personalVipDto.getIdCuenta());
    List<Map<String,String>> lista = new ArrayList<Map<String,String>>();
    lista.add(cuentaMap);

  
    personalVip.setTipoDoc(personalVipDto.getTipoDoc());
    personalVip.setNumDoc(personalVipDto.getNumDoc());
    personalVip.setName(personalVipDto.getName()); 
    personalVip.setApePat(personalVipDto.getApePat());
    personalVip.setApeMat(personalVipDto.getApeMat());
    personalVip.setAddress(personalVipDto.getAddress());
    personalVip.setCreateDate(new Date());
    personalVip.setUpdateDate(new Date());
    personalVip.setIdCuentas(lista);

    return personalVip;
  }

}
