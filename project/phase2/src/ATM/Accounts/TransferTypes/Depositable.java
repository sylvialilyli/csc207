package ATM.Accounts.TransferTypes;

import ATM.Accounts.Currency;

/**
 * The interface for depositable of the accounts
 */
public interface Depositable {
    void deposit(Currency amount);
}
