package com.itk.investmentfund.factory;

import com.itk.investmentfund.model.FundKind;
import com.itk.investmentfund.model.Split;
import com.itk.investmentfund.model.util.Tuple;

public enum InvestmentStyleFactory {

    SAVE {
        @Override
        public Split getInvestmentStyle() {
            return new Split(
                    Tuple.of(FundKind.POLISH, 20),
                    Tuple.of(FundKind.FOREIGN, 75),
                    Tuple.of(FundKind.PECUNIARY, 5)
            );
        }
    },

    BALANCED {
        @Override
        public Split getInvestmentStyle() {
            return new Split(
                    Tuple.of(FundKind.POLISH, 30),
                    Tuple.of(FundKind.FOREIGN, 60),
                    Tuple.of(FundKind.PECUNIARY, 10)
            );
        }
    },

    AGGRESSIVE {
        public Split getInvestmentStyle() {
            return new Split(
                    Tuple.of(FundKind.POLISH, 40),
                    Tuple.of(FundKind.FOREIGN, 20),
                    Tuple.of(FundKind.PECUNIARY, 40)
            );
        }
    };

    public Split getInvestmentStyle() {
        return null;
    }

}
