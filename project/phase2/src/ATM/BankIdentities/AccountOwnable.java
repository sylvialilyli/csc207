package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;

import java.util.ArrayList;

public interface AccountOwnable {
    public String getId();
    public void setPrimaryChq(Account acc) throws AlreadyPrimaryException;
    public ChequingAccount getPrimaryChq();
    public void addAccount(Account acc) throws UserNotOwnAccountException;
    public ArrayList<Account> getUserAccounts();
}
