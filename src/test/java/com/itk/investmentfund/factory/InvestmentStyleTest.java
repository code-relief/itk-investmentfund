package com.itk.investmentfund.factory;

import com.itk.investmentfund.model.FundKind;
import com.itk.investmentfund.model.Split;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentStyleTest {

    @Test
    public void shouldGetSaveInvestmentStyleAsExpected() {
        // when
        Split saveInvestmentStyle = InvestmentStyle.SAVE.getSplit();

        // then
        assertThat(saveInvestmentStyle.getFundKindsInvovled()).containsExactlyInAnyOrder(FundKind.POLISH, FundKind.PECUNIARY, FundKind.FOREIGN);
        assertThat(saveInvestmentStyle.getSplitValue(FundKind.POLISH)).isEqualTo(20);
        assertThat(saveInvestmentStyle.getSplitValue(FundKind.FOREIGN)).isEqualTo(75);
        assertThat(saveInvestmentStyle.getSplitValue(FundKind.PECUNIARY)).isEqualTo(5);
    }

    @Test
    public void shouldGetBalancedInvestmentStyleAsExpected() {
        // when
        Split balancedInvestmentStyle = InvestmentStyle.BALANCED.getSplit();

        // then
        assertThat(balancedInvestmentStyle.getFundKindsInvovled()).containsExactlyInAnyOrder(FundKind.POLISH, FundKind.PECUNIARY, FundKind.FOREIGN);
        assertThat(balancedInvestmentStyle.getSplitValue(FundKind.POLISH)).isEqualTo(30);
        assertThat(balancedInvestmentStyle.getSplitValue(FundKind.FOREIGN)).isEqualTo(60);
        assertThat(balancedInvestmentStyle.getSplitValue(FundKind.PECUNIARY)).isEqualTo(10);
    }

    @Test
    public void shouldGetAggressiveInvestmentStyleAsExpected() {
        // when
        Split aggressiveInvestmentStyle = InvestmentStyle.AGGRESSIVE.getSplit();

        // then
        assertThat(aggressiveInvestmentStyle.getFundKindsInvovled()).containsExactlyInAnyOrder(FundKind.POLISH, FundKind.PECUNIARY, FundKind.FOREIGN);
        assertThat(aggressiveInvestmentStyle.getSplitValue(FundKind.POLISH)).isEqualTo(40);
        assertThat(aggressiveInvestmentStyle.getSplitValue(FundKind.FOREIGN)).isEqualTo(20);
        assertThat(aggressiveInvestmentStyle.getSplitValue(FundKind.PECUNIARY)).isEqualTo(40);
    }

}