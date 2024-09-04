package com.krypton.account_money_transfer.dataaccess.coremoneytransfer;

import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferRequest;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "core-money-transfer-service", url = "${feign.client.core-money-transfer-service.baseUrl}")
public interface CoreMoneyTransferServiceClient {

    @PostMapping()
    ResponseEntity<CoreMoneyTransferResponse> coreMoneyTransfer(@RequestBody CoreMoneyTransferRequest request);
}
