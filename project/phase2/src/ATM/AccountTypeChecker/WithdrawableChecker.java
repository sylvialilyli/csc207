package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TransferTypes.Withdrawable;

/**
 * A checker used to check if an account is of type Withdrawable
 */
public class WithdrawableChecker implements TypeChecker {

    /** Check the account type of given account
     * @param acc The given account
     */
    public boolean check(Account acc){
        return (acc instanceof Withdrawable);
    }
}