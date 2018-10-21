package com.itk.investmentfund.model;

import com.itk.investmentfund.model.util.Tuple;

import java.util.HashMap;
import java.util.Map;

public class InvestmentPlan {

    private final Map<Fund, Amount> investments;
    private Integer amountUninvested;

    public InvestmentPlan() {
        this.investments = new HashMap<>();
        amountUninvested = 0;
    }

    public void addInvestment(Fund fund, Integer amount, Double percentage) {
        investments.putIfAbsent(fund, Amount.of(amount, percentage));
    }

    public void setAmountUninvested(Integer amountUninvested) {
        this.amountUninvested = amountUninvested;
    }

    public Map<Fund, Amount> getInvestments() {
        Map<Fund, Amount> result = new HashMap<>();
        result.putAll(investments);
        return result;
    }

    public Integer getAmountUninvested() {
        return amountUninvested;
    }

    @Override
    public String toString() {
        return "InvestmentPlan{" +
                "investments=" + investments +
                ", amountUninvested=" + amountUninvested +
                '}';
    }
}
