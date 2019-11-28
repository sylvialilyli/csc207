package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TransferTypes.TransferOutable;

/**
 * A checker used to check if an account is of type TransferOutable
 */
public class TransferOutableChecker implements TypeChecker {

    /** Check the account type of given account
     * @param acc The given account
     */
    public boolean check(Account acc){
        return (acc instanceof TransferOutable);
    }
}
