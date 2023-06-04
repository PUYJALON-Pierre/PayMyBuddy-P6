package com.paymybuddy.pay_my_buddy.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Model class TransferDTO, data transfer object to return specific informations about a transfer
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Data
public class TransferDTO {

  @NotNull @Positive
  private double amount;

  @NotNull @Email
  private String recipient;

  private String description;

  @NotNull
  private String currency;
}
