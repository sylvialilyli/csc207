package ATM.Accounts;

/** the interface for the account which could transfer out to other accounts*/
public interface TransferOutable {
    void transferOut(double amount);
}
