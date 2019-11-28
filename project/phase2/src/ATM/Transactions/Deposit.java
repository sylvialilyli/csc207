package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.Currency;
import ATM.Accounts.TransferTypes.Depositable;
import ATM.Accounts.TransferTypes.Withdrawable;
import ATM.BankSystem.Date;

import java.time.LocalDate;

/**
 * a class that represents a deposit action
 */
public class Deposit extends Transaction{
    private final Account fromAcc;
    private final Depositable toAcc;
    private LocalDate date = Date.getDate().getSystemCurrentTime();
    private final Currency amount;


    /**
     * Create a new Deposit.
     *
     * @param toAccount the account where fund will be deposit to.
     * @param amount the amount of fund will be deposit.
     */
    public Deposit(Depositable toAccount, Currency amount) {
        this.amount = amount;
        this.toAcc = toAccount;
        this.fromAcc = null;
    }

    public Currency getAmount() {
        return amount;
    }

    /**
     * Get the from Account. (Here is always null.)
     * @return Account if there is a from Account, null if there is not.
     */
    public Account getFromAcc() { return fromAcc; }

    /**
     * Get the to Account (where money will be deposit to).
     * @return Account if there is a to Account, null if there is not.
     */
    public Account getToAcc() {
        return (Account)toAcc;
    }

    /**
     * Get the date when this Deposit happened.
     * @return the date recorded.
     */
    public LocalDate getDate() {
        return date;
    }

    boolean possibleToBegin(){
        return true;
    }
    /**
     * Execute this Deposit. Set field happened as true if
     * this transaction is made.
     */
    @Override
    void begin() {
        toAcc.deposit((this.getAmount()));
        setHappened(true);
    }

    /**
     * Return a new Withdrawal as a reverse of the input transaction (same amount,
     * reversed from and to account).
     * @return Withdrawal
     * @throws ReverseNotPossibleException raised when not enough money in original to account.
     */
    @Override
    public Withdrawal reverse() throws ReverseNotPossibleException{
        Account fromAcc = getToAcc();
        if (fromAcc instanceof ChequingAccount) {
            if (fromAcc.getBalance().getAmount() <=0) {
                throw new ReverseNotPossibleException();
            }
        }
        if (getAmount().getAmount() > fromAcc.getAvailableCredit().getAmount()) {
            throw new ReverseNotPossibleException();
        }
        return new Withdrawal((Withdrawable) fromAcc, this.getAmount());
    }

    /**
     * Return a string representation of Deposit.
     * @return a String.
     */
    @Override
    public String toString() {
        return "Deposit{" +
                "to: " + getToAcc() +
                ", date:  " + getDate() +
                ", amount:" + getAmount() +
                '}';
    }
}
