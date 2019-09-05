package com.bitoex.bitopro.java.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class Pair {

    private final String base;
    private final String quote;
    private final String pair;
    private final int basePrecision;
    private final int quotePrecision;
    private final boolean maintain;
    private final BigDecimal maxLimitBaseAmount;
    private final BigDecimal minLimitBaseAmount;
    private final BigDecimal minMarketBuyQuoteAmount;
    private final int orderOpenLimit;

    @JsonCreator
    public Pair(String base, String quote, String pair, int basePrecision, int quotePrecision, boolean maintain, BigDecimal maxLimitBaseAmount, BigDecimal minLimitBaseAmount, BigDecimal minMarketBuyQuoteAmount, int orderOpenLimit) {
        this.base = base;
        this.quote = quote;
        this.pair = pair;
        this.basePrecision = basePrecision;
        this.quotePrecision = quotePrecision;
        this.maintain = maintain;
        this.maxLimitBaseAmount = maxLimitBaseAmount;
        this.minLimitBaseAmount = minLimitBaseAmount;
        this.minMarketBuyQuoteAmount = minMarketBuyQuoteAmount;
        this.orderOpenLimit = orderOpenLimit;
    }

    public String getBase() {
        return base;
    }

    public String getQuote() {
        return quote;
    }

    public String getPair() {
        return pair;
    }

    public int getBasePrecision() {
        return basePrecision;
    }

    public int getQuotePrecision() {
        return quotePrecision;
    }

    public boolean isMaintain() {
        return maintain;
    }

    public BigDecimal getMaxLimitBaseAmount() {
        return maxLimitBaseAmount;
    }

    public BigDecimal getMinLimitBaseAmount() {
        return minLimitBaseAmount;
    }

    public BigDecimal getMinMarketBuyQuoteAmount() {
        return minMarketBuyQuoteAmount;
    }

    public int getOrderOpenLimit() {
        return orderOpenLimit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
