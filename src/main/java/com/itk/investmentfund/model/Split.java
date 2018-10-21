package com.itk.investmentfund.model;

import com.itk.investmentfund.model.util.Tuple;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Split {

    // 100% :-)
    public final static int SUM = 100;
    private final Map<FundKind, Integer> splitDefinition;

    public Split(Tuple<FundKind, Integer>... splitDefinition) {
        this.splitDefinition = Arrays.stream(splitDefinition).collect(Collectors.toMap(Tuple::getX, Tuple::getY, (oldKey, newKey) -> oldKey));
        validate();
    }

    public Integer getSplitValue(FundKind fundKind) {
        return splitDefinition.get(fundKind);
    }

    public Set<FundKind> getFundKindsInvovled() {
        return splitDefinition.keySet();
    }

   @Override
    public String toString() {
        return "Split{" +
                "splitDefinition=" + splitDefinition +
                '}';
    }

    private void validate() {
        if (splitDefinition.isEmpty()) {
            throw new IllegalArgumentException("No split definition");
        }
        if (SUM != splitDefinition.values()
                .stream()
                .peek(val -> {
                    if (val <= 0) throw new IllegalArgumentException("Non positive values not allowed");
                })
                .mapToInt(i -> i.intValue())
                .sum()) {
            throw new IllegalArgumentException("Values do not sum to 100");
        }
    }

}
