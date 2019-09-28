package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Saving account class
 */
public class SavingAccount extends AssetAccount {
    private double availableCredit = getBalance();
    private ISaverPlan iSaverPlan;
    private final String accountNum = "004" + getOwnerID() + (InfoManager.getInfoManager().getAccountNum() + 1);

    /**
     * Constructor of saving account
     * Create a new saving account with ownerID and ISaverPlan
     *
     * @param ownerID the ID of the owner
     * @param the_plan the plan which result in the value of interest
     */
    public SavingAccount(String ownerID, ISaverPlan the_plan){
        super(ownerID);
        this.iSaverPlan = the_plan;
        setBalance(iSaverPlan.compute(getBalance()));
    }

    /**Getter method for available credit */
    @Override
    public double getAvailableCredit() {
        return availableCredit;
    }

    /**Getter method for account number */
    @Override
    public String getAccountNum(){
        return accountNum;
    }

    /**Setter method for saver plan */
    public void setSaverPlan(ISaverPlan s) {
        this.iSaverPlan = s;
    }

    /**@string a string combined with account type, account number */
    @Override
    public String toString() {
        return ("Saving Account " + this.accountNum);
    }

    /** @return a String that includes the summary of saving account: type, number, balance */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }
}
