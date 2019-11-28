package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.BankStaff;
import ATM.BankIdentities.User;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.BankSystem.Date;

import java.io.Serializable;
import java.util.*;

/**
 * Managing the saving and loading of all information
 * Including accounts, users, transactions
 */
public class InfoStorer implements Serializable {
    /**A mapping of Account ID to Account */
    private Map<String, Account> accountMap;

    /**A mapping of User ID to User */
    private Map<String, User> userMap;

    /**A mapping of User ID to BankStaff */
    private Map<String, BankStaff> staffMap;

    /**A mapping of Bank manager ID to Bank manager*/
    private Map<String, BankManager> bankManagerMap;

    /**
     * A Map stored request of account creation.
     * Key takes userID, values are requested account type.
     */
    private Map<String, String> requestMap;

    /**
     * A Map storing the password information of every bank identities.
     * Key takes String ID, values are encrypted password
     */
    private Map<String, String> passwordMap;

    /**
     * accTrans record history of Transaction of specific accounts.
     */
    private Map<String, Stack<Transaction>> accTransMap;
    /**
     * userTransList record history of Transaction of specific users.
     */
    private Map<String, Stack<Transaction>> userTransMap;

    /**
     * CashMachine in this ATM System.
     */
    private CashMachine cashMachine;

    private Date date;

    /**
     * Construct a infoStorer.
     */
    public InfoStorer(){
        this.accountMap = new HashMap<String, Account>();
        this.userMap = new HashMap<String, User>();
        this.staffMap = new HashMap<String, BankStaff>();
        this.bankManagerMap = new HashMap<String, BankManager>();
        this.requestMap = new HashMap<String, String>();
        this.passwordMap = new HashMap<String, String>();
        this.userTransMap = new HashMap<String, Stack<Transaction>>();
        this.accTransMap = new HashMap<String, Stack<Transaction>>();
        this.cashMachine = new CashMachine();
        this.date = Date.getDate();
    }

    /**
     * Get the accountMap
     * @return accountMap
     */
    public Map<String, Account> getAccountMap() {
        return accountMap;
    }

    /**
     * Get the userMap
     * @return userMap
     */
    public Map<String, User> getUserMap() {
        return userMap;
    }

    /**
     * Get the staffMap
     * @return staffMap
     */
    public Map<String, BankStaff> getStaffMap() {
        return staffMap;
    }

    /**
     * Get the bankManagerMap
     * @return bankManagerMap
     */
    public Map<String, BankManager> getBankManagerMap() { return bankManagerMap;}

    /**
     * Get the requestMap map
     * @return requestMap
     */
    public Map<String, String> getRequestMap() {
        return requestMap;
    }

    public Map<String, String> getPasswordMap() {
        return passwordMap;
    }

    public Map<String, Stack<Transaction>> getAccTransMap() {
        return accTransMap;
    }

    public Map<String, Stack<Transaction>> getUserTransMap() {
        return userTransMap;
    }

    /**
     * Get the CashMachine
     * @return CashMachine
     */
    public CashMachine getCashMachine() {
        return cashMachine;
    }

    public Date getDate() {
        return date;
    }
}
