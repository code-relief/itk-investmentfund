package com.itk.investmentfund.service;

import com.itk.investmentfund.model.*;
import com.itk.investmentfund.model.util.Tuple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentServiceImpl implements InvestmentService {

    private static final double CLOSE_TO_ZERO = 0.0001;

    @Override
    public InvestmentPlan createInvestmentPlan(Investment investment) {
        Split investmentSplit = investment.getInvestmentStyle().getSplit();
        int uninvestedAmount = calculateUninvestedAmount(investment.getAmount(), investmentSplit);
        int amountToInvest = investment.getAmount() - uninvestedAmount;
        Map<FundKind, Long> fundsCounted = investment.getFunds().stream().collect(Collectors.groupingBy(Fund::getKind, HashMap::new, Collectors.counting()));
        // FundKind: <dedicated amount, split final percentage>
        InvestmentPlan investmentPlan = new InvestmentPlan();
        investmentPlan.setAmountUninvested(uninvestedAmount);
        investment.getFunds().forEach(fund -> {
                    int fundAmount = investmentSplit.getSplitValue(fund.getKind()) * amountToInvest / 100 / fundsCounted.get(fund.getKind()).intValue();
                    investmentPlan.addInvestment(
                            fund, fundAmount,
                            round((double) fundAmount / amountToInvest * 100, 2)
                    );
                }
        );
        validateResult(amountToInvest, investmentSplit, investmentPlan);
        return investmentPlan;
    }

    private Integer calculateUninvestedAmount(int amount, Split investmentSplit) {
        return amount % Split.SUM;
    }

    private void validateResult(int amountToInvest, Split split, InvestmentPlan investmentPlan) {
        long difference = amountToInvest - investmentPlan.getInvestments().values().stream().map(amount -> amount.getAmount()).mapToInt(Integer::intValue).sum();
        if (difference != 0) {
            Set<FundKind> errors = new HashSet<>();
            Map<FundKind, Long> amountPerKind = investmentPlan.getInvestments().entrySet().stream().collect(Collectors.groupingBy(entry -> entry.getKey().getKind(), HashMap::new, Collectors.reducing(0L, e -> e.getValue().getX().longValue(), (e1, e2) -> e1 + e2)));
            split.getFundKindsInvovled().forEach(fundKind -> {
                int desiredPercentage = split.getSplitValue(fundKind);
                if (!amountPerKind.containsKey(fundKind)) {
                    throw new IllegalStateException(String.format("Missing fund kind: %s", fundKind));
                }
                double realPercentage = (double) amountPerKind.get(fundKind) / amountToInvest * 100;
                if (desiredPercentage - realPercentage > CLOSE_TO_ZERO) {
                    errors.add(fundKind);
                }
            });
            if (errors.size() > 0) {
                int surplus = (int) difference / errors.size();
                errors.forEach(fundKind -> {
                    Amount investment = investmentPlan.getInvestments().entrySet().stream().filter(e -> e.getKey().getKind() == fundKind).limit(1).reduce((e1, e2) -> e1).get().getValue();
                    investment.setAmount(investment.getAmount() + surplus);
                    investment.setPercentage(round((double) investment.getAmount() / amountToInvest * 100, 2));
                });
            }
        }
    }

    private double round(double value, int digitsNo) {
        if (digitsNo >= 0) {
            return Math.round(value * Math.pow(10, digitsNo)) / Math.pow(10, digitsNo);
        }
        return value;
    }
}
