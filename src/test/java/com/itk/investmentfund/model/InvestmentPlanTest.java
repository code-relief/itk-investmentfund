package com.itk.investmentfund.model;

import com.itk.investmentfund.model.util.Tuple;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentPlanTest {

    @Test
    public void investmentPlanShouldBeImmutable() {
        // having
        InvestmentPlan investmentPlan = new InvestmentPlan();
        investmentPlan.addInvestment(new Fund(123l, "fund 1", FundKind.POLISH), 1000, 13.456);
        investmentPlan.addInvestment(new Fund(234l, "fund 1", FundKind.POLISH), 1100, 23.456);
        investmentPlan.addInvestment(new Fund(345l, "fund 1", FundKind.FOREIGN), 1200, 33.456);
        investmentPlan.addInvestment(new Fund(456l, "fund 1", FundKind.FOREIGN), 1300, 43.456);
        investmentPlan.addInvestment(new Fund(567l, "fund 1", FundKind.FOREIGN), 1400, 53.456);
        investmentPlan.addInvestment(new Fund(678l, "fund 1", FundKind.POLISH), 1500, 63.456);

        // when
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        investments.put(new Fund(666l, "fund XXX", FundKind.PECUNIARY), Amount.of(1_000_000_000, 1_323_472.456));

        // then
        assertThat(investmentPlan.getInvestments()).hasSize(6);
        assertThat(investmentPlan.getInvestments().get(new Fund(666l, "fund XXX", FundKind.PECUNIARY))).isNull();
        assertThat(investmentPlan.getInvestments()).containsOnlyKeys(
                new Fund(123l, "fund 1", FundKind.POLISH),
                new Fund(234l, "fund 1", FundKind.POLISH),
                new Fund(345l, "fund 1", FundKind.FOREIGN),
                new Fund(456l, "fund 1", FundKind.FOREIGN),
                new Fund(567l, "fund 1", FundKind.FOREIGN),
                new Fund(678l, "fund 1", FundKind.POLISH)
        );
    }

    @Test
    public void shouldIgnoreDuplicatedFounds() {
        // having
        InvestmentPlan investmentPlan = new InvestmentPlan();
        investmentPlan.addInvestment(new Fund(123l, "fund 1", FundKind.POLISH), 1000, 13.456);
        investmentPlan.addInvestment(new Fund(123l, "fund 2", FundKind.POLISH), 1100, 23.456);
        investmentPlan.addInvestment(new Fund(123l, "fund 3", FundKind.FOREIGN), 1200, 33.456);
        investmentPlan.addInvestment(new Fund(234l, "fund 4", FundKind.PECUNIARY), 1300, 43.456);
        investmentPlan.addInvestment(new Fund(234l, "fund 5", FundKind.FOREIGN), 1400, 53.456);

        // when
        Map<Fund, Amount> investments = investmentPlan.getInvestments();

        // then
        assertThat(investmentPlan.getInvestments()).hasSize(2);
        assertThat(investmentPlan.getInvestments()).containsOnlyKeys(
                new Fund(123l, "fund 1", FundKind.POLISH),
                new Fund(234l, "fund 4", FundKind.PECUNIARY)
        );
    }

}