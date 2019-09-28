package ATM.BankIdentities;

public class UserNotOwnAccountExcetpiton extends Exception{
    public UserNotOwnAccountExcetpiton(String message) {
        super(message);
    }
}
