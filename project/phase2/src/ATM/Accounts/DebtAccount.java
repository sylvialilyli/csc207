package ATM.Accounts;

import java.util.ArrayList;

/**
 * A class that represent a debt account
 */
public abstract class DebtAccount extends BasicAccount {

    /**The maximum amount of debt an account can incur
     * In default, the limit is 1000*/
    private double limit = 1000;

    /**The account balance: the amount of money this account can incur
     * balance <= limit */
    private Currency balance;

    /**
     * Constructor of debt account
     * Create a new debt account with ownerID, limit
     *
     * @param ownerID the ID of the owner
     * @param  limit the limit of the debt account
     * @param type the currency type
     */
    public DebtAccount(ArrayList<String> ownerID, double limit, String type){
        super(ownerID);
        this.limit = limit;
        this.balance = new Currency(type, 0);
    }

    /**Getter method for account balance
     * @return account balance */
    public Currency getBalance(){
        return this.balance;
    }

    public String getCurrencyType(){
        return this.balance.getType();
    };

    /**Setter method for account balance with given newBalance */
    public void setBalance(double newBalance){
        this.balance.setAmount(newBalance);
    }

    /**Getter method for available credit
     * @return available credit: the amount of money which can be retrieved from this account
     */
    @Override
    public Currency getAvailableCredit() {
        return new Currency(this.balance.getType(), limit - this.balance.getAmount());
    }

    /**Transfer money with given amount into debt account
     * @param amount The given amount of money
     */
    @Override
    public void transferIn(Currency amount) {
        this.balance.subtract(amount);
    }

    /** Deposit money with given amount into this account
     * @param amount The given amount of money
     */
    @Override
    public void deposit(Currency amount){
        this.balance.subtract(amount);
    }

    /**Pay money with given amount from this debt account
     * @param amount The given amount of money
     */
    @Override
    public void pay(Currency amount){
        this.balance.add(amount);
    }

    /**Withdraw money with given amount from this account
     * @param amount The given amount of money
     */
    @Override
    public void withdraw(Currency amount){
        this.balance.add(amount);
    }

    /**Getter method for limit of the debt account */
    public double getLimit(){
        return this.limit;
    }

    /**Setter method for limit of the debt account*/
    public void setLimit(double newLimit){
        this.limit = newLimit;
    }

    /** Calculating the net balance*/
    @Override
    public Currency getNetBalance(){
        return new Currency(this.balance.getType(),-balance.getAmount());
    }
}