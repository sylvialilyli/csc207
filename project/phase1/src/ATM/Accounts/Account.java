package ATM.Accounts;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * An abstract class that represents a bank account
 */
public abstract class Account implements Withdrawable, TransferInable, Payable, Depositable, Serializable {

    /**The time when an account is created */
    private final LocalDateTime dateOfCreation = LocalDateTime.now();

    /**The ID of the User whom this account belongs to */
    private String ownerID;

    /**
     * Constructor of account
     * Create a new account with ownerID
     *
     * @param ownerID the ID of the owner
     */
    public Account(String ownerID){
        this.ownerID = ownerID;
    }

    /**Return the User ID */
    public String getOwnerID(){ return ownerID;}


    /**Return the date of Creation */
    public LocalDateTime getDateOfCreation(){
        return dateOfCreation;
    }

    /**Abstract Method for deposit money to account*/
    public abstract void deposit(double amount);

    /**Abstract Method for getting available credit from account */
    public abstract double getAvailableCredit();

    /**Abstract Method for getting account number from account */
    public abstract String getAccountNum();

    /**Abstract Method for getting balance from account */
    public abstract double getBalance();

    /**Abstract Method for setting balance of account */
    public abstract void setBalance(double amount);

    /**Abstract Method for transfering money to account */
    public abstract void transferIn(double amount);

    /**Abstract Method for paying money from account */
    public abstract void pay(double amount);

    /**Abstract Method for withdraw money from account */
    public abstract void withdraw(double amount);

    public abstract String getSummary();
}
