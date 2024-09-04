package com.krypton.account_money_transfer.api.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MoneyTransferRequest {

    @NotBlank(message = "cif is blank")
    @Size(min = 10, max = 10, message = "Invalid CIFs")
    private String cif;

    @NotBlank(message = "sourceAccount is blank")
    @Size(min = 11, max = 11, message = "Invalid sourceAccount")
    private String sourceAccount;

    @NotBlank(message = "destinationAccount is blank")
    @Size(min = 24, max = 24, message = "Invalid destinationAccount")
    private String destinationAccount;

    @NotBlank(message = "amount is blank")
    private String amount;

    @NotBlank(message = "currency is blank")
    private String currency;

    @NotBlank(message = "reason  is blank")
    private String reason;

    @Size(max = 10, message = "Notes size exceeded")
    private String notes;
}
