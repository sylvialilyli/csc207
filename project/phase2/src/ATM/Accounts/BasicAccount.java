package ATM.Accounts;

import ATM.Accounts.TransferTypes.Depositable;
import ATM.Accounts.TransferTypes.Payable;
import ATM.Accounts.TransferTypes.TransferInable;
import ATM.Accounts.TransferTypes.Withdrawable;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class BasicAccount extends Account implements TransferInable, Withdrawable, Payable, Depositable, Serializable{

        /**
         * Constructor of BasicAccount
         * Create a new account with ownerID
         *
         * @param ownerID the ID of the owner
         */

    public BasicAccount(ArrayList<String> ownerID){
        super(ownerID);
    }

        @Override
        /**Abstract Method for deposit money to account*/
        public abstract void deposit(Currency amount);

        @Override
        /**Abstract Method for paying money from account */
        public abstract void pay(Currency amount);

        @Override
        /**Abstract Method for withdraw money from account */
        public abstract void withdraw(Currency amount);

        /**Abstract Method for transferring money to account */
        public abstract void transferIn(Currency amount);

    }
