package ATM.BankIdentities;

public class NoSuchAccountException extends Exception {
    public NoSuchAccountException() {
        super();
    }
    /** Initialize a NoSuchAccountException
     *
     * @param message the message send
     */
    public NoSuchAccountException(String message) {
        super(message);
    }
}
