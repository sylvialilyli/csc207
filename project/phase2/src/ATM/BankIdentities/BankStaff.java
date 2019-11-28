package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.BankSystem.Date;
import ATM.InfoHandling.InfoStorer;
import ATM.InfoHandling.RecordWriter;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;

import java.util.ArrayList;
import java.util.Map;

/** A class that represent a BankStaff*/
public class BankStaff extends BankEmployee implements PrivilegeLevelB, AccountOwnable{
    /** The id of the user */
    private final String id;
    /** The account list of the user */
    private ArrayList<Account> accounts = new ArrayList<>();
    /** The primary account of the user */
    private ChequingAccount primaryChq;
    public BankStaff(int totalNum){
        this.id = "710" + totalNum + 1;
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

    @Override
    public String createUser(String type, InfoStorer infoStorer) {
        String userID = super.createUser(type, infoStorer);
        RecordWriter rw = new RecordWriter();
        rw.write(this.getId() + " create a user on " + Date.getDate());
        return userID;
    }

    @Override
    public void restock(CashMachine machine, Money money) {
        super.restock(machine, money);
        RecordWriter rw = new RecordWriter();
        rw.write(this.getId() + " has restocked " + money+ "on " + Date.getDate());
    }
}

