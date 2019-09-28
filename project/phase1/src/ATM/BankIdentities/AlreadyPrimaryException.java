package ATM.BankIdentities;
/** This is a AlreadyPrimaryException */
public class AlreadyPrimaryException extends Exception {
    public AlreadyPrimaryException() {
    }

    /** Initialize a AlreadyPrimaryException
     *
     * @param message the message send
     */

    public AlreadyPrimaryException(String message) {
        super(message);
    }
}
