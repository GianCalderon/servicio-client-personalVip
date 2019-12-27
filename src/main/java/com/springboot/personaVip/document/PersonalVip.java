package com.springboot.personaVip.document;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "cliente-personalVip")
public class PersonalVip {

	  @Id
	  private String id;
	  
	  @NotNull(message = "User's tipoDoc must not be null")
	  private String tipoDoc;
	  
	  @NotNull(message = "User's numDoc must not be null")
	  @Indexed(unique = true)
	  private String numDoc;
	  
	  @NotNull(message = "User's name must not be null")
	  private String name;
	  
	  @NotNull(message = "User's apePat must not be null")
	  private String apePat;
	  
	  @NotNull(message = "User's apeMat must not be null")

	  private String apeMat;
	  
	  @NotNull(message = "User's address must not be null")
	  private String address;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd")
	  private Date createDate;
	  
	  
	  @JsonFormat(pattern = "yyyy-MM-dd")
	  private Date updateDate;
	  
	  private List<Account> listAccount;


  public PersonalVip() {

  }



public PersonalVip(String tipoDoc, String numDoc, String name, String apePat, String apeMat, String address) {
	super();
	this.tipoDoc = tipoDoc;
	this.numDoc = numDoc;
	this.name = name;
	this.apePat = apePat;
	this.apeMat = apeMat;
	this.address = address;

}




}
