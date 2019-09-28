package ATM.BankIdentities;

public class NoSuchTypeException extends Exception {
    public NoSuchTypeException() {
        super();
    }
    /** Initialize a NoSuchTypeException
     *
     * @param message the message send
     */
    public NoSuchTypeException(String message) {
        super(message);
    }
}
