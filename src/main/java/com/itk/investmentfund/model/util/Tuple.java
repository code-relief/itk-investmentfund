package com.itk.investmentfund.model;

public class Tuple <X, Y> {

    private final X key;
    private final Y value;

    public Tuple(X key, Y value) {
        this.key = key;
        this.value = value;
    }

    public static <X, Y> Tuple<X, Y> of (X key, Y value) {
        return new Tuple<>(key, value);
    }

    public X getKey() {
        return key;
    }

    public Y getValue() {
        return value;
    }
}
