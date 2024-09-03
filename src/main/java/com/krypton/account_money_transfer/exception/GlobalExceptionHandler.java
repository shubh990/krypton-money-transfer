package com.krypton.account_money_transfer.exception;

import com.krypton.account_money_transfer.api.data.GetMoneyTransferResponse;
import com.krypton.account_money_transfer.api.data.MoneyTransferResponse;
import com.krypton.account_money_transfer.api.data.Status;
import com.krypton.account_money_transfer.repository.service.SaveTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    SaveTransaction saveTransaction;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
                errorMap.put(e.getField(), e.getDefaultMessage()));
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MoneyTransferFailure.class)
    public ResponseEntity<MoneyTransferResponse> accountNotFoundException(MoneyTransferFailure ex) {
        GetMoneyTransferResponse getMoneyTransferResponse = new GetMoneyTransferResponse(ex.getFailureReasons());
        MoneyTransferResponse response = getMoneyTransferResponse.getMoneyTransferResponse(Status.FAILED, ex.getTransactionNumber());
        saveTransaction.saveTransactionInDB(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
