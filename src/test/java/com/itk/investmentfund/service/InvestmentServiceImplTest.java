package com.itk.investmentfund.service;

import com.itk.investmentfund.factory.InvestmentStyle;
import com.itk.investmentfund.model.*;

import com.itk.investmentfund.model.util.Tuple;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class InvestmentServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final InvestmentService investmentService = new InvestmentServiceImpl();

    @Test
    public void acceptanceTestFirstScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Polski 2", FundKind.POLISH);
        Fund fund3 = new Fund(3l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund4 = new Fund(4l, "Fundusz Zagraniczny 2", FundKind.FOREIGN);
        Fund fund5 = new Fund(5l, "Fundusz Zagraniczny 3", FundKind.FOREIGN);
        Fund fund6 = new Fund(6l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        funds.add(fund4);
        funds.add(fund5);
        funds.add(fund6);
        Integer amount = 10_000;
        InvestmentStyle investmentStyle = InvestmentStyle.SAVE;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(0);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(6);
        // polish 1
        assertThat(investments.get(fund1).getAmount()).isEqualTo(1000);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(10);
        // polish 2
        assertThat(investments.get(fund2).getAmount()).isEqualTo(1000);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(10);
        // foreign 1
        assertThat(investments.get(fund3).getAmount()).isEqualTo(2500);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(25);
        // foreign 2
        assertThat(investments.get(fund4).getAmount()).isEqualTo(2500);
        assertThat(investments.get(fund4).getPercentage()).isEqualTo(25);
        // foreign 3
        assertThat(investments.get(fund5).getAmount()).isEqualTo(2500);
        assertThat(investments.get(fund5).getPercentage()).isEqualTo(25);
        // pecuniary 1
        assertThat(investments.get(fund6).getAmount()).isEqualTo(500);
        assertThat(investments.get(fund6).getPercentage()).isEqualTo(5);
    }

    @Test
    public void acceptanceTestSecondScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Polski 2", FundKind.POLISH);
        Fund fund3 = new Fund(3l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund4 = new Fund(4l, "Fundusz Zagraniczny 2", FundKind.FOREIGN);
        Fund fund5 = new Fund(5l, "Fundusz Zagraniczny 3", FundKind.FOREIGN);
        Fund fund6 = new Fund(6l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        funds.add(fund4);
        funds.add(fund5);
        funds.add(fund6);
        Integer amount = 10_001;
        InvestmentStyle investmentStyle = InvestmentStyle.SAVE;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(1);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(6);
        // polish 1
        assertThat(investments.get(fund1).getAmount()).isEqualTo(1000);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(10);
        // polish 2
        assertThat(investments.get(fund2).getAmount()).isEqualTo(1000);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(10);
        // foreign 1
        assertThat(investments.get(fund3).getAmount()).isEqualTo(2500);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(25);
        // foreign 2
        assertThat(investments.get(fund4).getAmount()).isEqualTo(2500);
        assertThat(investments.get(fund4).getPercentage()).isEqualTo(25);
        // foreign 3
        assertThat(investments.get(fund5).getAmount()).isEqualTo(2500);
        assertThat(investments.get(fund5).getPercentage()).isEqualTo(25);
        // pecuniary 1
        assertThat(investments.get(fund6).getAmount()).isEqualTo(500);
        assertThat(investments.get(fund6).getPercentage()).isEqualTo(5);
    }

    @Test
    public void acceptanceTestThirdScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Polski 2", FundKind.POLISH);
        Fund fund3 = new Fund(3l, "Fundusz Polski 3", FundKind.POLISH);
        Fund fund4 = new Fund(4l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund5 = new Fund(5l, "Fundusz Zagraniczny 2", FundKind.FOREIGN);
        Fund fund6 = new Fund(6l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        funds.add(fund4);
        funds.add(fund5);
        funds.add(fund6);
        Integer amount = 10_000;
        InvestmentStyle investmentStyle = InvestmentStyle.SAVE;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(0);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(6);
        // polish 1
        assertThat(investments.get(fund1).getAmount()).isEqualTo(668);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(6.68);
        // polish 2
        assertThat(investments.get(fund2).getAmount()).isEqualTo(666);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(6.66);
        // foreign 1
        assertThat(investments.get(fund3).getAmount()).isEqualTo(666);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(6.66);
        // foreign 2
        assertThat(investments.get(fund4).getAmount()).isEqualTo(3750);
        assertThat(investments.get(fund4).getPercentage()).isEqualTo(37.5);
        // foreign 3
        assertThat(investments.get(fund5).getAmount()).isEqualTo(3750);
        assertThat(investments.get(fund5).getPercentage()).isEqualTo(37.5);
        // pecuniary 1
        assertThat(investments.get(fund6).getAmount()).isEqualTo(500);
        assertThat(investments.get(fund6).getPercentage()).isEqualTo(5);
    }

    @Test
    public void shouldThrowExpectedExceptionWhenInsufficiendNumberOfFundKinds() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Polski 2", FundKind.POLISH);
        Fund fund3 = new Fund(3l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund4 = new Fund(4l, "Fundusz Zagraniczny 2", FundKind.FOREIGN);
        Fund fund5 = new Fund(5l, "Fundusz Zagraniczny 3", FundKind.FOREIGN);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        funds.add(fund4);
        funds.add(fund5);
        Integer amount = 10_000;
        InvestmentStyle investmentStyle = InvestmentStyle.SAVE;
        Investment investment = new Investment(amount, investmentStyle, funds);

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Missing fund kind: PECUNIARY");

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then...
    }

    @Test
    public void shouldCorrectlyInvestInMinimumAggresiveScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund3 = new Fund(3l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        Integer amount = 10_000;
        InvestmentStyle investmentStyle = InvestmentStyle.AGGRESSIVE;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(0);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(3);
        // polish
        assertThat(investments.get(fund1).getAmount()).isEqualTo(4000);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(40);
        // foreign
        assertThat(investments.get(fund2).getAmount()).isEqualTo(2000);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(20);
        // pecuniary
        assertThat(investments.get(fund3).getAmount()).isEqualTo(4000);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(40);
    }

    @Test
    public void shouldCorrectlyInvestInMinimumBalancedScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund3 = new Fund(3l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        Integer amount = 10_000;
        InvestmentStyle investmentStyle = InvestmentStyle.BALANCED;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(0);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(3);
        // polish
        assertThat(investments.get(fund1).getAmount()).isEqualTo(3000);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(30);
        // foreign
        assertThat(investments.get(fund2).getAmount()).isEqualTo(6000);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(60);
        // pecuniary
        assertThat(investments.get(fund3).getAmount()).isEqualTo(1000);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(10);
    }

    @Test
    public void shouldDealWithNoAmountScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund3 = new Fund(3l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        Integer amount = 0;
        InvestmentStyle investmentStyle = InvestmentStyle.BALANCED;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(0);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(3);
        // polish
        assertThat(investments.get(fund1).getAmount()).isEqualTo(0);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(0);
        // foreign
        assertThat(investments.get(fund2).getAmount()).isEqualTo(0);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(0);
        // pecuniary
        assertThat(investments.get(fund3).getAmount()).isEqualTo(0);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(0);
    }

    @Test
    public void shouldDealWithTooLittleAmountScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund3 = new Fund(3l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        Integer amount = 43;
        InvestmentStyle investmentStyle = InvestmentStyle.BALANCED;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(43);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(3);
        // polish
        assertThat(investments.get(fund1).getAmount()).isEqualTo(0);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(0);
        // foreign
        assertThat(investments.get(fund2).getAmount()).isEqualTo(0);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(0);
        // pecuniary
        assertThat(investments.get(fund3).getAmount()).isEqualTo(0);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(0);
    }

    @Test
    public void shouldDealWithMinimumAmountScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund3 = new Fund(3l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        Integer amount = 100;
        InvestmentStyle investmentStyle = InvestmentStyle.AGGRESSIVE;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(0);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(3);
        // polish
        assertThat(investments.get(fund1).getAmount()).isEqualTo(40);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(40);
        // foreign
        assertThat(investments.get(fund2).getAmount()).isEqualTo(20);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(20);
        // pecuniary
        assertThat(investments.get(fund3).getAmount()).isEqualTo(40);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(40);
    }

    @Test
    public void shouldDealWithLittleEnoughAmountScenario() {
        // having
        Set<Fund> funds = new HashSet<>();
        Fund fund1 = new Fund(1l, "Fundusz Polski 1", FundKind.POLISH);
        Fund fund2 = new Fund(2l, "Fundusz Zagraniczny 1", FundKind.FOREIGN);
        Fund fund3 = new Fund(3l, "Fundusz Pieniężny 1", FundKind.PECUNIARY);
        funds.add(fund1);
        funds.add(fund2);
        funds.add(fund3);
        Integer amount = 143;
        InvestmentStyle investmentStyle = InvestmentStyle.BALANCED;
        Investment investment = new Investment(amount, investmentStyle, funds);

        // when
        InvestmentPlan investmentPlan = investmentService.createInvestmentPlan(investment);

        // then
        assertThat(investmentPlan.getAmountUninvested()).isEqualTo(43);
        Map<Fund, Amount> investments = investmentPlan.getInvestments();
        assertThat(investments).hasSize(3);
        // polish
        assertThat(investments.get(fund1).getAmount()).isEqualTo(30);
        assertThat(investments.get(fund1).getPercentage()).isEqualTo(30);
        // foreign
        assertThat(investments.get(fund2).getAmount()).isEqualTo(60);
        assertThat(investments.get(fund2).getPercentage()).isEqualTo(60);
        // pecuniary
        assertThat(investments.get(fund3).getAmount()).isEqualTo(10);
        assertThat(investments.get(fund3).getPercentage()).isEqualTo(10);
    }


}