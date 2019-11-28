package ATM.Accounts;

import java.util.ArrayList;

/**
 * a class that represent a Line of credit account
 */
public class LineOfCredit extends DebtAccount{

    /** The account number */
    private final String accountNum;

    private final String lineOfCreditCode = "002";

    /**
     * Constructor of line of credit account
     * Create a new line of credit account with ownerID, limit and the total number of accounts created
     *
     * @param ownerID the ID of the owner
     * @param limit the limit of this line of credit account
     * @param totalNumAcc the total number of accounts created
     * @param type the currency type
     */
    public LineOfCredit(ArrayList<String> ownerID, double limit, int totalNumAcc, String type) {
        super(ownerID, limit, type);
        this.accountNum = lineOfCreditCode + (totalNumAcc+1);
    }

    /**Transfer money with given amount out of line of credit account
     * @param amount the given amount of money
     */
    public void transferOut(Currency amount){
        getBalance().add(amount);
    }

    /**Getter for getting line of credit account number */
    public String getAccountNum(){
        return this.accountNum;
    }

    /**
     * Return a String representation of this account
     * @return a string combined with account type, account number
     */
    @Override
    public String toString() {
        return (this.getCurrencyType() + " Line of Credit " + this.accountNum);
    }

    /**
     * Return the summary of this account
     * @return a String combined with account type, account number, balance
     */
    @Override
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }

}