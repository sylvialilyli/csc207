package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Credit account class
 */
public class CreditAccount extends DebtAccount {
    private final String accountNum = "001" + getOwnerID()+ (InfoManager.getInfoManager().getAccountNum() + 1);

    /**
     * Constructor of credit account
     * Create a new credit account with ownerID and limit
     *
     * @param ownerID the ID of the owner
     * @param limit the limit of this credit account
     */
    public CreditAccount(String ownerID, double limit){
        super(ownerID, limit);
    }

    /**Getter method for credit account number*/
    public String getAccountNum(){
        return this.accountNum;
    }

    /**@string a string combined with account type, account number */
    @Override
    public String toString() {
        return ("CreditAccount" + ": "  + this.accountNum);
    }

    /** @return a String that includes the summary of credit account: type, number, balance */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }

}