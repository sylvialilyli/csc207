package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.User;
import ATM.Machine.CashMachine;
import ATM.Transactions.TransactionManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Managing the saving and loading of all information
 * Including accounts, users, transactions
 */
public class InfoStorer {
    /**A mapping of Account ID to Account */
    private Map<String, Account> accountMap;

    /**A mapping of User ID to User */
    private Map<String, User> userMap;

    /**A mapping of Bank manager ID to Bank manager*/
    private Map<String, BankManager> bankManagerMap;

    /**A TransactionManager which has all the transaction information*/
    private TransactionManager transactionManager;

    /***
     * Map stored request of account creation. Key takes userID, values are requested account type.
     */
    private Map<String, String> accountCreationRequest;

    /***
     * CashMachine in this ATM System.
     */
    private CashMachine cashMachine;

    /***
     * Construct a infoStorer.
     */
    public InfoStorer(){
        this.accountMap = new HashMap<String, Account>();
        this.userMap = new HashMap<String, User>();
        this.bankManagerMap = new HashMap<String, BankManager>();
        this.transactionManager = TransactionManager.getTransactionManager();
        this.accountCreationRequest = new HashMap<>();
        this.cashMachine = new CashMachine();
    }

    /***
     * Get the accounMap
     * @return accountMap
     */
    public Map<String, Account> getAccountMap() {
        return accountMap;
    }

    /***
     * Get the userMap
     * @return userMap
     */
    public Map<String, User> getUserMap() {
        return userMap;
    }

    /***
     * Get the bankManagerMap
     * @return bankManagerMap
     */
    public Map<String, BankManager> getBankManagerMap() { return bankManagerMap;}

    /***
     * Get the transactionManager
     * @return transactionManager
     */
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    /***
     * Get the accountCreationRequest map
     * @return accountCreationRequest
     */
    public Map<String, String> getAccountCreationRequest() {
        return accountCreationRequest;
    }

    /***
     * Get the CashMachine
     * @return CashMachine
     */
    public CashMachine getCashMachine() {
        return cashMachine;
    }

    /***
     * Add a user to userMap
     * @param newUser the user to be added
     */
    public void addUser(User newUser){
        this.userMap.put(newUser.getId(), newUser);
    }

    /***
     * Add a bank manager to bankManagerMap
     * @param newBankManager the BankManager to be added
     */
    public void addBankManager(BankManager newBankManager){
        this.bankManagerMap.put(newBankManager.getId(), newBankManager);
    }

    /***
     * Add an account to accountMap
     * @param newAccount the Account to be added
     */
    public void addAccount(Account newAccount){
        this.accountMap.put(newAccount.getAccountNum(), newAccount);
    }
}
