package ATM.Accounts;

import ATM.Accounts.TransferTypes.TransferOutable;

import java.util.ArrayList;

/**
 * An abstract class that represent an asset account
 */
public abstract class AssetAccount extends BasicAccount implements TransferOutable {

    /**
     * Account balance represents how much money is stored in this account
     */
    private Currency balance;

    /**
     * Constructor of asset account
     * Create a new asset account with ownerID
     * @param ownerID the ID of the owner
     * @param type the currency type
     */
    AssetAccount(ArrayList<String> ownerID, String type){
           super(ownerID);
           this.balance = new Currency(type, 0);
    }

    /** Returen the account balance*/
    @Override
    public Currency getBalance() {
        return balance;
    }

    public String getCurrencyType(){
        return this.balance.getType();
    };

    /**Set the balance of the asset account
     * @param balance The new balance
     */
    @Override
    public void setBalance(double balance) {
        this.balance.setAmount(balance);
    }

    /**Transfer in money with given amount to the asset account
     * @param amount The given amount of money
     */
    @Override
    public void transferIn(Currency amount) {
        balance.add(amount);
    }

    /**Deposit money with given amount to the asset account
     * @param amount The given amount of money
     */
    @Override
    public void deposit(Currency amount){
        balance.add(amount);
    }

    /**Transfer out money with given amount from the asset account
     * @param amount The given amount of money
     */
    @Override
    public void transferOut(Currency amount) {
        balance.subtract(amount);
    }

    /**Pay money with given amount from the asset account
     * @param amount The given amount of money
     */
    @Override
    public void pay(Currency amount) {
        balance.subtract(amount);
    }

    /**Withdraw money with given amount from the asset account
     * @param amount The given amount of money
     */
    @Override
    public void withdraw(Currency amount) {
        balance.subtract(amount);
    }

    /**Calculating the net balance*/
    @Override
    public Currency getNetBalance(){
        return balance;
    }
}
