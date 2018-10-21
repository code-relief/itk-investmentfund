package com.itk.investmentfund.model;

import java.util.Objects;

public class Fund {

    private final Long id;
    private final String name;
    private final FundKind kind;

    public Fund(Long id, String name, FundKind kind) {
        this.id = id;
        this.name = name;
        this.kind = kind;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FundKind getKind() {
        return kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return Objects.equals(id, fund.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fund{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kind=" + kind +
                '}';
    }
}
