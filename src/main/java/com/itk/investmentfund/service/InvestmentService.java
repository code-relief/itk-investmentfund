package com.itk.investmentfund.service;

import com.itk.investmentfund.model.Investment;
import com.itk.investmentfund.model.InvestmentPlan;

public interface InvestmentService {

    InvestmentPlan createInvestmentPlan(Investment investment);

}
