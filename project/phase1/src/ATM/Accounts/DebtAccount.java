package ATM.Accounts;

/**
 * Debt account abstract class
 */
public abstract class DebtAccount extends Account {
    private double limit;
    private String ownerID;
    private double balance;
    private double availableCredit;

    /**
     * Constructor of debt account
     * Create a new debt account with ownerID and limit
     *
     * @param ownerID the ID of the owner
     * @param  limit the limit of the debt account
     */
    public DebtAccount(String ownerID, double limit){
        super(ownerID);
        this.ownerID = ownerID;
        this.limit = limit;
        this.availableCredit = limit - balance;
    }

    /**Getter method for account balance */
    public double getBalance(){
        return this.balance;
    }

    /**Setter method for account balance */
    public void setBalance(double newBalance){
        this.balance = newBalance;
    }

    /**Getter method for account available credit to spend*/
    @Override
    public double getAvailableCredit() {
        return availableCredit;
    }

    /**Transfer in method for transfering money into debt accounts*/
    @Override
    public void transferIn(double amount) {
        this.balance -= amount;
    }

    @Override
    public void deposit(double amount){
        this.balance -= amount;
    }

    /**Pay method for paying with debt account*/
    @Override
    public void pay(double amount){
        this.balance += amount;
    }

    /**Withdraw method for withdrawing with debt account*/
    @Override
    public void withdraw(double amount){
        this.balance += amount;
    }

    /**Getter method for limit of the debt account */
    public double getLimit(){
        return this.limit;
    }

    /**Setter method for limit of the debt account*/
    public void setLimit(double newLimit){
        this.limit = newLimit;
    }
}