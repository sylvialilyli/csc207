package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.Currency;
import ATM.Accounts.TransferTypes.Depositable;
import ATM.Accounts.TransferTypes.Withdrawable;
import ATM.BankSystem.Date;

import java.time.LocalDate;

/**
 * a class that represents a withdrawal action
 */
public class Withdrawal extends Transaction {
    private final Withdrawable fromAcc;
    private final Account toAcc;
    private LocalDate date = Date.getDate().getSystemCurrentTime();
    private final Currency amount;

    /**
     * Create a new Withdrawal
     * @param fromAcc the account where money will be withdrawn.
     * @param amount the amount of fund will be withdrawn.
     */
    public Withdrawal(Withdrawable fromAcc, Currency amount) {
        this.amount = amount;
        this.fromAcc = fromAcc;
        this.toAcc = null;
    }

    public Currency getAmount() {
        return amount;
    }

    /**
     * Get the from Account (where money be withdrawn).
     * @return Account if there is a from Account, null if there is not.
     */
    public Account getFromAcc() {
        return (Account)fromAcc;
    }

    /**
     * Get the to Account (Here is null).
     * @return Account if there is a to Account, null if there is not.
     */
    public Account getToAcc() {
        return toAcc;
    }

    /**
     * Get the date when this Withdrawal happened.
     * @return the date recorded.
     */
    public LocalDate getDate() {
        return date;
    }

    /*public int getToAccNum() {
        return toAccNum;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }*/

    /**
     * Execute this Withdrawal. Set field happened as true if this
     * Withdrawal is executed.
     */
    @Override
    void begin() {
        Currency amount = getAmount();
        amount.setAmount((int)(amount.getAmount()));
        fromAcc.withdraw(amount);
        setHappened(true);
    }

    /**
     * Return a new Deposit as a reverse of the input transaction (same amount,
     * reversed from and to account).
     * @return Deposit
     */
    @Override
    public Deposit reverse() {
        Depositable toAcc = (Depositable)getFromAcc();
        return new Deposit(toAcc ,this.getAmount());
    }

    /***
     * Check if the transaction is possible to begin
     * @return if the amount of transaction is within limit
     * @throws TransactionAmountOverLimitException
     */
    boolean possibleToBegin() throws TransactionAmountOverLimitException{
        Account acc = getFromAcc();
        if (acc instanceof ChequingAccount) {
            if (acc.getBalance().getAmount() <= 0) {
                throw new TransactionAmountOverLimitException();
            }
        }
        if (getAmount().getAmount() > acc.getAvailableCredit().getAmount()) {
            throw new TransactionAmountOverLimitException();
        }
        return true;
    }

    /**
     * Return a String representation of Deposit.
     * @return string
     */
    @Override
    public String toString() {
        return "Withdrawal{" +
                "from: " + getFromAcc() +
                ", date: " + getDate() +
                ", amount: " + getAmount() +
                "}";
    }
}
