package ATM.AccountTypeChecker;

import ATM.Accounts.Account;

/***
 * An interface allow to check types of account
 */
public interface TypeChecker {

    boolean check(Account acc);
}

