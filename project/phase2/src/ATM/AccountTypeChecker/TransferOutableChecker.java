package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TransferOutable;

/***
 * A checker used to check if an account is of type TransferOutable
 */
public class TransferOutableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof TransferOutable);
    }
}
