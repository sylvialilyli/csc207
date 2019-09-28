package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.Payable;

/***
 * A checker used to check if an account is of type Payable
 */
public class PayableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof Payable);
    }
}