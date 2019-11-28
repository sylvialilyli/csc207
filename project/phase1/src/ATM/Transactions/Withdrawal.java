package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.Withdrawable;

import java.time.LocalDateTime;

/***
 * Withdrawal class
 */
public class Withdrawal extends Transaction {
    private final Withdrawable fromAcc;
    private final Account toAcc;
    private final LocalDateTime time;

    /***
     * Create a new Withdrawal
     * @param fromAcc the account where money will be withdrawn.
     * @param amount the amount of fund will be withdrawn.
     */
    public Withdrawal(Withdrawable fromAcc, double amount) {
        super(amount);
        this.fromAcc = fromAcc;
        this.toAcc = null;
        this.time = LocalDateTime.now();
    }

    /***
     * Get the from Account (where money be withdrawn).
     * @return Account if there is a from Account, null if there is not.
     */
    public Account getFromAcc() {
        return (Account)fromAcc;
    }

    /***
     * Get the to Account (Here is null).
     * @return Account if there is a to Account, null if there is not.
     */
    public Account getToAcc() {
        return toAcc;
    }

    /***
     * Get the time when this Withdrawal happened.
     * @return the time recorded.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /*public int getToAccNum() {
        return toAccNum;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }*/

    /***
     * Execute this Withdrawal. Set field happened as true if this
     * Withdrawal is executed.
     * @throws TransactionAmountOverLimitException if the amount is too large.
     */
    @Override
    void begin() throws TransactionAmountOverLimitException{
        Account acc = getFromAcc();
        if (acc instanceof ChequingAccount) {
            if (acc.getBalance() <= 0) {
                throw new TransactionAmountOverLimitException();
            }
        }
        if (getAmount() > acc.getAvailableCredit()) {
            throw new TransactionAmountOverLimitException();
        }
        getFromAcc().withdraw(this.getAmount());
        setHappened(true);
    }

    /***
     * Return a new Deposit as a reverse of the input transaction (same amount,
     * reversed from and to account).
     * @return Deposit
     */
    @Override
    public Deposit reverse() {
        Account toAcc = getFromAcc();
        return new Deposit(toAcc, this.getAmount());
    }

    /***
     * Return a String representation of Deposit.
     * @return string
     */
    @Override
    public String toString() {
        return "Withdrawal{" +
                "from: " + getFromAcc() +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}";
    }
}
