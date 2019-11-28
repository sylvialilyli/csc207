package ATM.Accounts.TransferTypes;

import ATM.Accounts.Currency;

/** the interface for the account which could transfer out to other accounts*/
public interface TransferOutable {
    void transferOut(Currency amount);
}
