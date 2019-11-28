package ATM.Accounts;

import ATM.Accounts.TransferTypes.TransferInable;
import ATM.BankSystem.Date;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * An abstract class that represents a bank account
 */
public abstract class Account implements Serializable {

    /**The date when an account is created */
    private LocalDate dateOfCreation = Date.getDate().getSystemCurrentTime();

    /**The number of owners*/
    private int numOwner;

    /**The ID of the User whom this account belongs to */
    private ArrayList<String> ownerID;

    /**
     * Constructor of account
     * Create a new account with ownerID
     *
     * @param ownerID the ID of the owner
     */
    public Account(ArrayList<String> ownerID){
        this.ownerID = ownerID;
        this.numOwner = ownerID.size();
    }

    /**Return the User ID */
    public ArrayList<String> getOwnerID() {
        return ownerID;
    }

    /**Return the number of users */
    public int getNumOwner(){return numOwner;}

    /**Return the date of Creation */
    public LocalDate getDateOfCreation(){
        return dateOfCreation;
    }

    public void addOwner(String newOwner){
        ownerID.add(newOwner);
    }

    public boolean containsOwner(String owner){
        return owner.contains(owner);
    }

    public void removeOwner(String owner){
        ownerID.remove(owner);
    }

    /**Abstract Method for getting available credit from account */
    public abstract Currency getAvailableCredit();

    /**Abstract Method for getting account number from account */
    public abstract String getAccountNum();

    /**Abstract Method for getting balance from account */
    public abstract Currency getBalance();

    public abstract String getCurrencyType();

    /**Abstract Method for setting balance of account */
    public abstract void setBalance(double amount);

    public abstract String getSummary();

    /** Abstract method for calculating the net balance*/
    public abstract Currency getNetBalance();
}
