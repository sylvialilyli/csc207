package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;

/***
 * A checker used to check if an account is of type ChequingAccount
 */
public class ChequingChecker implements TypeChecker {

    public boolean check(Account acc) {
        return (acc instanceof ChequingAccount);
    }
}
