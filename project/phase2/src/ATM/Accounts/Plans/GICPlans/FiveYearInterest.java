package ATM.Accounts.Plans.GICPlans;

import java.io.Serializable;

public class FiveYearInterest extends GICPlan implements Serializable {

    private final double  interestRate = 0.6;
    private final int periodOfMonth = 5*12;

    public FiveYearInterest(){
        setInterestRate(interestRate);
        setPeriodOfMonth(periodOfMonth);
    }

    @Override
    public String toString(){
        return "Five Years GIC Plan with interesting rate: " + interestRate;
    }
}
