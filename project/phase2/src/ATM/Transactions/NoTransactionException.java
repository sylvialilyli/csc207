package ATM.Transactions;

public class NoTransactionException extends Exception {
    public NoTransactionException(String message) {
        super(message);
    }
}
