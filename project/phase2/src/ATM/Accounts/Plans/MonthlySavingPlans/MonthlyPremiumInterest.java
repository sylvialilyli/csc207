package ATM.Accounts.Plans.MonthlySavingPlans;

import ATM.Accounts.Plans.Plan;
import ATM.BankSystem.Date;

import java.io.Serializable;

/**High interest plan with monthly interesting rate above 3%*/
public class MonthlyPremiumInterest extends SavingPlan {
    private final double interestRate = 0.03;

    public MonthlyPremiumInterest(){
        setInterestRate(interestRate);
    }

    /** Return a String representation of this saving plan
     * @return String information about this saving plan */
    @Override
    public String toString(){
        return "This is a SavingPlan with monthly interest: " + interestRate;
    }
}