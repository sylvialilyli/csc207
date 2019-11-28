package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TimeSensitive;

public class TimeSensitiveChecker implements TypeChecker{

        /** Check the account type of given account
         * @param acc The given account
         */
        public boolean check(Account acc) {
            return (acc instanceof TimeSensitive);
        }

}
