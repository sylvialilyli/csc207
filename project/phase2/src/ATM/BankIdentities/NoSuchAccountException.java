package ATM.BankIdentities;

public class NoSuchAccountException extends Exception {
    /** Initialize a NoSuchAccountException
     *
     * @param message the message send
     */
    public NoSuchAccountException(String message) {
        super(message);
    }
}
