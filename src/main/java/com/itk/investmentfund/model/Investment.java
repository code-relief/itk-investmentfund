package com.itk.investmentfund.model;

import com.itk.investmentfund.factory.InvestmentStyle;

import java.util.Set;

public class Investment {

    private final Integer amount;
    private final InvestmentStyle investmentStyle;
    private final Set<Fund> funds;

    public Investment(Integer amount, InvestmentStyle investmentStyle, Set<Fund> funds) {
        this.amount = amount;
        this.investmentStyle = investmentStyle;
        this.funds = funds;
    }

    public Integer getAmount() {
        return amount;
    }

    public InvestmentStyle getInvestmentStyle() {
        return investmentStyle;
    }

    public Set<Fund> getFunds() {
        return funds;
    }
}
