package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TransferTypes.Depositable;

/**
 * A checker used to check if an account is of type Depositable
 */
public class DepositableChecker implements TypeChecker {

    /** Check the account type of given account
     * @param acc The given account
     */
    public boolean check(Account acc){
        return (acc instanceof Depositable);
    }
}