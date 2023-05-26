package com.paymybuddy.pay_my_buddy.DTO;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferDTO {

  
  @NotNull
  @Positive
  private double amount;
  
  @NotNull
  @Email
  private String recipient;


  private String description;
  
  @NotNull
  private String currency;
}
