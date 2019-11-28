package ATM.Accounts;

import java.time.LocalDate;
/**
 * The plan with monthly interest 1%
 */

public class MonthlyInterest implements ISaverPlan {
    private double interestRate;
    private LocalDate currentDate = LocalDate.now();

    /**
     * Constructor for the monthly interest class
     * Creates monthly interest with interest rate
     *
     * @param interestRate the interest rate of the monthly interest
     */
    public MonthlyInterest(double interestRate){
        this.interestRate = interestRate;
    }

    /** Calculate and return the amount of money that is earned from interest */
    @Override
    public double compute(double amount) {
        if (currentDate.getDayOfMonth() == 1){
                return amount * interestRate;
            }
            return amount;
    }

    public String toString(){
        return "This is a SavingPlan with monthly interest; "+interestRate;}

    }

