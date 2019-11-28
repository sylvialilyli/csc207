package ATM.BankIdentities;

public class UserNotOwnAccountException extends Exception{
    public UserNotOwnAccountException(String message) {
        super(message);
    }
}
