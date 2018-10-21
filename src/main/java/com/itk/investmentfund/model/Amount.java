package com.itk.investmentfund.model;

import com.itk.investmentfund.model.util.Tuple;

public class Amount extends Tuple<Integer, Double> {

    public static Amount of (Integer amount, Double percentage) {
        return new Amount(amount, percentage);
    }

    public Amount(Integer amount, Double percentage) {
        super(amount, percentage);
    }

    public Integer getAmount() {
        return getX();
    }

    public Double getPercentage() {
        return getY();
    }

    public void setAmount(Integer amount) {
        setX(amount);
    }

    public void setPercentage(Double percentage) {
        setY(percentage);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount=" + getX() +
                ", percentage=" + getY() +
                '}';
    }
}
