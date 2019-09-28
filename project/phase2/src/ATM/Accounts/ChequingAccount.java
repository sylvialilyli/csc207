package ATM.Accounts;


import ATM.InfoHandling.InfoManager;

/**
 * Chequing account class
 */
public class ChequingAccount extends AssetAccount{
    private double overDraftLimit = 100;
    private final String accountNum = "003" + getOwnerID() + (InfoManager.getInfoManager().getAccountNum() + 1);

    /**
     * Constructor of chequing account
     * Create a new chequing account with ownerID
     *
     * @param ownerID the ID of the owner
     */
    public ChequingAccount(String ownerID){
        super(ownerID);
    }

    /**Setter method for setting over draft limit */
    public void setOverDraftLimit(double limit){
        this.overDraftLimit = limit;
    }

    /**Getter method for returning the over draft limit */
    public double getOverDraftLimit() {return overDraftLimit;
    }

    /**Getter method for getting the available credit of chequing account */
    @Override
    public double getAvailableCredit(){
        double a = (getBalance()+overDraftLimit);
        return a;
    }

    /**Getter method for getting the account number of chequing account */
    @Override
    public String getAccountNum(){
        return accountNum;
    }

    /**@string a string combined with account type, account number */
    @Override
    public String toString() {
        return ("Chquing Account " + this.accountNum);
    }

    /** @return a String that includes the summary of chequing account: type, number, balance */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }

}
