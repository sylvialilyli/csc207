package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;

import java.util.ArrayList;
import java.util.Map;

/** a class that access to a user's account and primary account */
public class User extends BankIdentity implements AccountOwnable{

    /** The id of the user */
    private final String id;
    /** The account list of the user */
    private ArrayList<Account> accounts = new ArrayList<>();
    /** The primary account of the user */
    private ChequingAccount primaryChq;

    /**
     * Create a new user
     * @param totalUserNum The total number of the users created*/
    public User(int totalUserNum) {
        this.id = "020" + totalUserNum + 1;
    }

    /** Return the Id of the user
     * @return id
     */
    public String getId(){
        return id;
    }



    /** Set the primary chequing account
     *
     * @param acc the account we want to set as primary
     * @throws AlreadyPrimaryException
     * */
    public void setPrimaryChq(Account acc) throws AlreadyPrimaryException{
        if (acc instanceof ChequingAccount) {
            if (acc.getOwnerID().contains(id)) {
                if (acc == getPrimaryChq()) {
                    throw new AlreadyPrimaryException("This account is already " +
                            "a primary chequing account.");
                } else {
                    this.primaryChq = ((ChequingAccount)acc);
                }
            }
        }
    }

    /** Get primary chequing account */
    public ChequingAccount getPrimaryChq(){
        return primaryChq;
    }

    /** Add the given account to the account list
     * @param acc the account be added*/
    public void addAccount(Account acc) throws UserNotOwnAccountException {
        if (acc.getOwnerID().contains(id)) {
            this.accounts.add(acc);
        } else {
            throw new UserNotOwnAccountException("This account is not owned by user: " + id);
        }
    }

    public ArrayList<Account> getUserAccounts(){
        return this.accounts;
    }
}
