package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;

/***
 * A checker used to check if an account is of type TransferInable
 */
public class TransferInableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof TransferInable);
    }
}