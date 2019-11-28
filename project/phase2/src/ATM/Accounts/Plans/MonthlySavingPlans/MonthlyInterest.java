package ATM.Accounts.Plans.MonthlySavingPlans;

import ATM.Accounts.Plans.Plan;
import ATM.BankSystem.Date;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The normal saving plans with monthly interest less than 3%
 */

public class MonthlyInterest extends SavingPlan {
    private final double interestRate = 0.01;

    public MonthlyInterest(){
        setInterestRate(interestRate);
    }

    /** Return a String representation of this saving plan
     * @return String information about this saving plan */
    @Override
    public String toString(){
        return "This is a SavingPlan with monthly interest: " + interestRate;}
}