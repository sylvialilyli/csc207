package ATM.Transactions;

public class TransactionAmountOverLimitException extends Exception {

    public TransactionAmountOverLimitException() {
        super("Not enough balance to complete transaction.");
    }
}
