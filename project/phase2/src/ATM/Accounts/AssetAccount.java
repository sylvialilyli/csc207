package ATM.Accounts;

/**
 * Asset account abstract class
 */
public abstract class AssetAccount extends Account implements TransferOutable {
    private double balance;

    /**
     * Constructor of asset account
     * Create a new asset account with ownerID
     *
     * @param ownerID the ID of the owner
     */
    AssetAccount(String ownerID){
           super(ownerID);
    }

    /**Getter for asset accounts*/
    @Override
    public double getBalance() {
        return balance;
    }

    /**Method for Transfer in money to the asset account*/
    @Override
    public void transferIn(double amount) {
        balance += amount;
    }

    /**Method for deposit money to the asset account*/
    @Override
    public void deposit(double amount){
        balance += amount;
    }

    /**Method for Transfer out money from the asset account*/
    @Override
    public void transferOut(double amount) {
        balance -= amount;
    }

    /**Setter method for the balance of the asset account*/
    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**Method for pay money from the asset account*/
    @Override
    public void pay(double amount) {
        balance -= amount;
    }

    /**Method for withdraw money from the asset account*/
    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }
}
