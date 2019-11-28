package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;

public class CADBaseChecker implements TypeChecker {
    /** Check if account's currency base is CAD
     * @param acc The given account
     */
    public boolean check(Account acc) {
        return (acc.getCurrencyType().equals("CAD"));
    }
}
