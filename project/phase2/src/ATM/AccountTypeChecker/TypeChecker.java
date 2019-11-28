package ATM.AccountTypeChecker;

import ATM.Accounts.Account;

/**
 * An interface allow to check types of account
 */
public interface TypeChecker {
    /** Check the account type of given account
     * @param acc The given account
     */
    boolean check(Account acc);
}

