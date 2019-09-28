package ATM.Machine;

public class CashNotWithdrawableException extends Exception{
    public CashNotWithdrawableException() {
    }

    public CashNotWithdrawableException(String message) {
        super(message);
    }
}
