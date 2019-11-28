package ATM.Transactions;

public class NotCADBaseAccountException extends Exception{
    public NotCADBaseAccountException(){
        super("The currency base of account chosen should be CAD");
    }
}
