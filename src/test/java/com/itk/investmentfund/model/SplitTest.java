package com.itk.investmentfund.model;

import com.itk.investmentfund.model.util.Tuple;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SplitTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldCorrectlyStoreSplitDefinition() {
        // having
        Tuple[] splitDefinition = {
            Tuple.of(FundKind.FOREIGN, 33),
                    Tuple.of(FundKind.PECUNIARY, 33),
                    Tuple.of(FundKind.POLISH, 34)
        };

        // when
        Split split = new Split(splitDefinition);

        // then
        Assertions.assertThat(split.getFundKindsInvovled()).hasSize(3);
        Assertions.assertThat(split.getFundKindsInvovled()).containsExactlyInAnyOrder(FundKind.FOREIGN, FundKind.PECUNIARY, FundKind.POLISH);
        Assertions.assertThat(split.getSplitValue(FundKind.FOREIGN)).isEqualTo(33);
        Assertions.assertThat(split.getSplitValue(FundKind.PECUNIARY)).isEqualTo(33);
        Assertions.assertThat(split.getSplitValue(FundKind.POLISH)).isEqualTo(34);
    }

    @Test()
    public void shouldDetectInconsistentDataAndThrowException() {
        // having
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Values do not sum to 100");

        // when
        Split split = new Split(
                Tuple.of(FundKind.FOREIGN, 10),
                Tuple.of(FundKind.PECUNIARY, 20),
                Tuple.of(FundKind.POLISH, 30)
        );

        // then ...
    }

    @Test()
    public void shouldDetectEmptySplitDefinition() {
        // having
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("No split definition");

        // when
        Split split = new Split();

        // then ...
    }

    @Test
    public void shouldCorrectlyReduceDuplicatedSplitDefinitions() {
        // having
        Tuple[] splitDefinition = {
                Tuple.of(FundKind.FOREIGN, 33),
                Tuple.of(FundKind.PECUNIARY, 33),
                Tuple.of(FundKind.POLISH, 34),
                Tuple.of(FundKind.FOREIGN, 1234567),
                Tuple.of(FundKind.PECUNIARY, 2345678),
                Tuple.of(FundKind.POLISH, 0)
        };

        // when
        Split split = new Split(splitDefinition);

        // then
        Assertions.assertThat(split.getFundKindsInvovled()).hasSize(3);
        Assertions.assertThat(split.getFundKindsInvovled()).containsExactlyInAnyOrder(FundKind.FOREIGN, FundKind.PECUNIARY, FundKind.POLISH);
        Assertions.assertThat(split.getSplitValue(FundKind.FOREIGN)).isEqualTo(33);
        Assertions.assertThat(split.getSplitValue(FundKind.PECUNIARY)).isEqualTo(33);
        Assertions.assertThat(split.getSplitValue(FundKind.POLISH)).isEqualTo(34);
    }

    @Test()
    public void shouldNotAcceptNegativeValuesAndThrowException() {
        // having
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Non positive values not allowed");

        // when
        Split split = new Split(
                Tuple.of(FundKind.FOREIGN, 50),
                Tuple.of(FundKind.PECUNIARY, -20),
                Tuple.of(FundKind.POLISH, 70)
        );

        // then ...
    }

    @Test()
    public void shouldNotAcceptZeroValuesAndThrowException() {
        // having
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Non positive values not allowed");

        // when
        Split split = new Split(
                Tuple.of(FundKind.FOREIGN, 50),
                Tuple.of(FundKind.PECUNIARY, 0),
                Tuple.of(FundKind.POLISH, 50)
        );

        // then ...
    }

    @Test()
    public void shouldDealWithBigNumbers() {
        // having
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Values do not sum to 100");

        // when
        Split split = new Split(
                Tuple.of(FundKind.FOREIGN, Integer.MAX_VALUE),
                Tuple.of(FundKind.PECUNIARY, 20),
                Tuple.of(FundKind.POLISH, 30)
        );

        // then ...
    }

}