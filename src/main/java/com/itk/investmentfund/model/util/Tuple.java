package com.itk.investmentfund.model.util;

public class Tuple<X, Y> {

    private X x;
    private Y y;

    public static <X, Y> Tuple<X, Y> of(X x, Y y) {
        return new Tuple<>(x, y);
    }

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
