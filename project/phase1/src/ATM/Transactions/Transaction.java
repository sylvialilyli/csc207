package ATM.Transactions;

import ATM.Accounts.Account;

import java.io.Serializable;

/***
 * Abstract class Transaction
 */
public abstract class Transaction implements Serializable {
    private final double amount;
    private boolean happened;

    /***
     * Create a new Transaction
     * @param amount the amount of money this transaction involves.
     */
    public Transaction(double amount) {
        this.amount = amount;
    }

    //public abstract int getFromAccNum();

    //public abstract int getToAccNum();

    /***
     * Abstract method. Get the account money will be took out.
     * @return Account if there is one, null if there is not.
     */
    public abstract Account getFromAcc();

    /***
     * Abstract method. Get the account money will be put in.
     * @return Account if there is one, null if there is not.
     */
    public abstract Account getToAcc();

    /***
     * Return the amount of money this transaction involves.
     * @return amount in double format.
     */
    public double getAmount() {
        return amount;
    }

    /***
     * Set the boolean field happened as input.
     * @param happened a boolean field, showing if this transaction
     *                 if happened.
     */
    public void setHappened(boolean happened) {
        this.happened = happened;
    }

    /***
     * Return if this transaction is happened.
     * @return true if it is happened.
     */
    public boolean isHappened(){
        return happened;
    }

    /***
     * Abstract method. Execute this transaction.
     * @throws TransactionAmountOverLimitException if the amount this
     * transaction involves is more than what can be took out.
     */
    abstract void begin() throws TransactionAmountOverLimitException;  //make it default

    /***
     * Abstract method. Return a reversed version of the input transaction.
     * @return the reversed Transaction. toAccount as fromAccount, fromAccount as toAccount, same amount.
     * @throws ReverseNotPossibleException
     */
    public abstract Transaction reverse() throws ReverseNotPossibleException;

}
