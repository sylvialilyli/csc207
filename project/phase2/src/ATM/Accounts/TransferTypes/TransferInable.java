package ATM.Accounts.TransferTypes;

import ATM.Accounts.Currency;

/** a transferInable interface for the accounts which could be transferred in */
public interface TransferInable {
   void transferIn(Currency amount);
}
