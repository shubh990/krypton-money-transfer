package com.krypton.account_money_transfer.api.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MoneyTransferRequest {

    @NotBlank
    @Size(min = 10, max = 10, message = "Invalid CIFs")
    private String cif;

    @NotBlank
    @Size(min = 11, max = 11, message = "Invalid sourceAccount")
    private String sourceAccount;

    @NotBlank
    @Size(min = 24, max = 24, message = "Invalid destinationAccount")
    private String destinationAccount;

    @NotBlank
    private String amount;

    @NotBlank
    private String currency;

    @NotBlank
    private String reason;

    @Size(max = 10, message = "Notes size exceeded")
    private String notes;
}
