package ATM.Transactions;


import ATM.Accounts.Account;
import ATM.Accounts.Currency;
import ATM.Accounts.TransferTypes.Payable;
import ATM.BankSystem.Date;
import ATM.InfoHandling.BillWriter;
import ATM.org.openexchangerates.oerjava.OpenExchangeRates;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * a class that represents a bill paying action
 */
public class PayBill extends Transaction{
    private final String to;
    private final Payable fromAcc;
    private final Account toAcc;
    private LocalDate time = Date.getDate().getSystemCurrentTime();
    private final Currency amount;
    private  BillWriter writer = new BillWriter();


    /**
     * Create a new PayBill.
     *
     * @param fromAccount the account where fund will be extracted from.
     * @param amount the amount of fund will be paid.
     */
    public PayBill(Payable fromAccount, String to, Currency amount) {
        this.amount = amount;
        this.fromAcc = fromAccount;
        this.toAcc = null;
        this.to = to;
    }

    public Currency getAmount() {
        return amount;
    }

    /**
     * Get the from Account (which will pay the bill).
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
     * Get the time when this PayBill happened.
     * @return the time recorded.
     */
    public LocalDate getTime() {
        return time;
    }

    boolean possibleToBegin() throws TransactionAmountOverLimitException{
        if (amount.getAmount() > getFromAcc().getAvailableCredit().getAmount()) {
            throw new TransactionAmountOverLimitException();
        }
        return true;
    }

    /**
     * Execute this PayBill. Set field happened as true if this
     * PayBill is executed.
     */
    @Override
    void begin(){
        fromAcc.pay(this.getAmount());
        setHappened(true);
        writer.write(this.toString());
    }


    /**
     * Not possible to reverse a PayBill
     * @throws ReverseNotPossibleException raised when not enough money in original to account.
     */
    @Override
    public Transaction reverse() throws ReverseNotPossibleException{
        throw new ReverseNotPossibleException();
    }

    /**
     * Get the destination of this bill payment.
     * @return the name whom this payment is made to.
     */
    public String getTo() {
        return to;
    }

    /*public String record(String cont) {
        writer.write();
        String userId = getFromAcc().getOwnerID();
        return (userId + "," + getFromAcc() + "," + getTo() + ","
                + getDate() + "," + getAmount() + "\n")
                ;
    }*/

    /**
     * Return a string representation of PayBill.
     * @return a String.
     */
    @Override
    public String toString() {
        return "PayBill{" +
                "from: " + getFromAcc() +
                ", to: " + getTo()  +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}";
    }
}
