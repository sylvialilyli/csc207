package ATM.Accounts;

import ATM.Accounts.Plans.Plan;

import java.util.ArrayList;

/**
 * A class that represent a saving account
 */
public class SavingAccount extends AssetAccount implements TimeSensitive{

    /** The saving plan for this account*/
    private Plan Plan;

    /** The account number */
    private final String accountNum;

    private final String savingCode = "004";

    /**
     * Constructor of saving account
     * Create a new saving account with ownerID, ISaverPlan and the total number of accounts created
     *
     * @param ownerID the ID of the owner
     * @param plan the plan which result in the value of interest
     * @param totalNumAcc the total number of accounts created
     * @param type the currency type
     */
    public SavingAccount(ArrayList<String> ownerID, Plan plan, int totalNumAcc, String type){
        super(ownerID, type);
        this.Plan = plan;
        this.accountNum = savingCode +  (totalNumAcc + 1);
        setBalance(Plan.compute(getBalance().getAmount()));
    }

    /**Getter method for available credit
     * @return available credit: the amount of money which can be retrieved from this account
     */
    @Override
    public Currency getAvailableCredit() {
        return getBalance();
    }

    /**Getter method for account number
     * @return account number
     */
    @Override
    public String getAccountNum(){
        return accountNum;
    }

    /**Setter method for saver plan */
    public void setSaverPlan(Plan s) {
        this.Plan = s;
    }

    /**
     * Return a String representation of this account
     * @return a string combined with account type, account number
     */
    @Override
    public String toString() {
        return (this.getCurrencyType() + " Saving Account " + this.accountNum);
    }

    @Override
    public void compute(){
        setBalance(Plan.compute(getBalance().getAmount()));
    }

    /**
     * Return the summary of this account
     * @return a String combined with account type, account number, balance
     */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }
}
