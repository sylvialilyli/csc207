package ATM.Accounts.Plans.GICPlans;

public class TwoYearInterest extends GICPlan{
    private final double  interestRate = 0.15;
    private final int periodOfMonth = 2*12;

    public TwoYearInterest(){
        setInterestRate(interestRate);
        setPeriodOfMonth(periodOfMonth);
    }

    @Override
    public String toString(){
        return "Two Years GIC Plan with interesting rate: " + interestRate;
    }
}
