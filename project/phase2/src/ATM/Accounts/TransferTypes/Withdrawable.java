package ATM.Accounts.TransferTypes;

import ATM.Accounts.Currency;

/** A withdrawable account can be withdraw amount of money*/
public interface Withdrawable {
      void withdraw(Currency amount);
}
