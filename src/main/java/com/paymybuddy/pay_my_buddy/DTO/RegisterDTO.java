package com.paymybuddy.pay_my_buddy.DTO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Model class RegisterDTO, data transfer object to return specific informations for registration
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Data
public class RegisterDTO {

  @NotNull
  private String firstname;

  @NotNull
  private String lastname;

  @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;

  @NotNull @Email
  private String email;

  @NotNull
  private String password;

}
