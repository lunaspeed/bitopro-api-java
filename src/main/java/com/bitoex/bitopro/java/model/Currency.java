package com.bitoex.bitopro.java.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class Currency {

    private final String currency;
    private final boolean deposit;
    private final int depositConfirmation;
    private final BigDecimal maxDailyWithdraw;
    private final BigDecimal maxWithdraw;
    private final BigDecimal minWithdraw;
    private final boolean withdraw;
    private final BigDecimal withdrawFee;


    @JsonCreator
    public Currency(String currency, boolean deposit, int depositConfirmation, BigDecimal maxDailyWithdraw, BigDecimal maxWithdraw, BigDecimal minWithdraw, boolean withdraw, BigDecimal withdrawFee) {
        this.currency = currency;
        this.deposit = deposit;
        this.depositConfirmation = depositConfirmation;
        this.maxDailyWithdraw = maxDailyWithdraw;
        this.maxWithdraw = maxWithdraw;
        this.minWithdraw = minWithdraw;
        this.withdraw = withdraw;
        this.withdrawFee = withdrawFee;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isDeposit() {
        return deposit;
    }

    public int getDepositConfirmation() {
        return depositConfirmation;
    }

    public BigDecimal getMaxDailyWithdraw() {
        return maxDailyWithdraw;
    }

    public BigDecimal getMaxWithdraw() {
        return maxWithdraw;
    }

    public BigDecimal getMinWithdraw() {
        return minWithdraw;
    }

    public boolean isWithdraw() {
        return withdraw;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
