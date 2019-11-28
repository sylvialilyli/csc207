package ATM.Accounts.Plans.MonthlySavingPlans;

import ATM.Accounts.Plans.Plan;
import ATM.BankSystem.Date;

import java.io.Serializable;

public class SavingPlan implements Plan, Serializable {
    private double interestRate;

    /** Calculate and return the amount of money that is earned from interest */
    protected void setInterestRate(double newInterestRate){
        this.interestRate = newInterestRate;
    }

    public double getInterestRate(){
        return interestRate;
    }

    @Override
    public double compute(double amount) {
        Date date = Date.getDate();
        if (date.getSystemCurrentTime().getDayOfMonth() == 1){
            return amount * (interestRate+1);
        }
        return amount;
    }
}