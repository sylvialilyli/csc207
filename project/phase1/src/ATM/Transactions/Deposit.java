package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.Depositable;

import java.time.LocalDateTime;

/***
 * Deposit class
 */
public class Deposit extends Transaction{
    private final Account fromAcc;
    private final Depositable toAcc;
    private final LocalDateTime time;

    /***
     * Create a new Deposit.
     *
     * @param toAccount the account where fund will be deposit to.
     * @param amount the amount of fund will be deposit.
     */
    public Deposit(Depositable toAccount, double amount) {
        super(amount);
        this.toAcc = toAccount;
        this.fromAcc = null;
        this.time = LocalDateTime.now();
    }

    /*public Deposit(User user, double amount) {
        super(amount);
        this.toAcc = user.getAccManager().getPrimaryChq();
        this.fromAcc = null;
        this.time = toAcc.getCurrentTime();
    }*/

    /*public int getFromAccNum() {
        return fromAccNum;
    }*/

    /*public int getToAccNum() {
        return toAccNum;
    }*/

    /***
     * Get the from Account. (Here is always null.)
     * @return Account if there is a from Account, null if there is not.
     */
    public Account getFromAcc() { return fromAcc; }

    /***
     * Get the to Account (where money will be deposit to).
     * @return Account if there is a to Account, null if there is not.
     */
    public Account getToAcc() {
        return (Account)toAcc;
    }

    /***
     * Get the time when this Deposit happened.
     * @return the time recorded.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /***
     * Execute this Deposit. Set field happened as true if
     * this transaction is made.
     */
    @Override
    void begin() {
        getToAcc().deposit(this.getAmount());
        setHappened(true);
    }

    /***
     * Return a new Withdrawal as a reverse of the input transaction (same amount,
     * reversed from and to account).
     * @return Withdrawal
     * @throws ReverseNotPossibleException raised when not enough money in original to account.
     */
    @Override
    public Withdrawal reverse() throws ReverseNotPossibleException{
        Account fromAcc = getToAcc();
        if (fromAcc instanceof ChequingAccount) {
            if (fromAcc.getBalance() <=0) {
                throw new ReverseNotPossibleException();
            }
        }
        if (getAmount() > fromAcc.getAvailableCredit()) {
            throw new ReverseNotPossibleException();
        }
        return new Withdrawal(fromAcc, this.getAmount());
    }

    /***
     * Return a string representation of Deposit.
     * @return a String.
     */
    @Override
    public String toString() {
        return "Deposit{" +
                "to: " + getToAcc() +
                ", time:  " + getTime() +
                ", amount:" + getAmount() +
                '}';
    }
}
