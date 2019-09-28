package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.Withdrawable;

/***
 * A checker used to check if an account is of type Withdrawable
 */
public class WithdrawableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof Withdrawable);
    }
}