package com.paymybuddy.pay_my_buddy.DTO;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RegisterDTO {

  @NotNull
  @Email
  private String email;
  
  @NotNull
  private String firstName;
  
  @NotNull
  private String lastName;
 
  
  @NotNull
  private String password;

  
  @NotNull
  private Date birthdate;
  
  
}
