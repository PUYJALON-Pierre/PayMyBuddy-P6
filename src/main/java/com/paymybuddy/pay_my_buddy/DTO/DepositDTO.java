package com.paymybuddy.pay_my_buddy.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Model class DepositDTO, data transfer object to return specific informations about a deposit
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Data
public class DepositDTO {

  @NotNull @Positive
  private double amount;

  @NotNull
  private String iban;

  private String description;

  @NotNull
  private String currency;

}
