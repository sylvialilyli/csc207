package ATM.Machine;

public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
