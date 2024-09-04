package com.krypton.account_money_transfer.dataaccess.accountservice;

import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "${feign.client.account-service.baseUrl}")
public interface AccountServiceClient {

    @GetMapping("/accounts/{cif}/{accountNumber}")
    ResponseEntity<AccountServiceResponse> getAccountDetails(@PathVariable("cif") String cif,
                                                              @PathVariable("accountNumber") String accountNumber
    );
}
