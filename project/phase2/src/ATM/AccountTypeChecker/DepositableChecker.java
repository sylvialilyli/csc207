package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.Depositable;

/***
 * A checker used to check if an account is of type Depositable
 */
public class DepositableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof Depositable);
    }
}