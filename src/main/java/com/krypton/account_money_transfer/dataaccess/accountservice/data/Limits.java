package com.krypton.account_money_transfer.dataaccess.accountservice.data;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Limits {
    private BigDecimal monthly;
    private BigDecimal daily;
}