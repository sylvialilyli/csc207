package ATM.Accounts.TransferTypes;

import ATM.Accounts.Currency;

/** the interface for the account which could pay the bills*/
public interface Payable {
    void pay(Currency amount);
}
