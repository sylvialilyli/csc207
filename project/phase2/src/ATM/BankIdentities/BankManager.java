package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.CashMachine;
import ATM.Machine.CashNotWithdrawableException;
import ATM.Machine.Money;
import ATM.Machine.NotEnoughMoneyException;
import ATM.Transactions.*;

import java.util.EmptyStackException;
import java.util.Map;

/** A class that represent a manager of the bank
 * Help Users manage their accounts
 * Have more access than BankEmployee
 */
public class BankManager extends BankEmployee implements PrivilegeLevelA{

    /** The id of this bank manager */
    private final String id;

    /** Creates a new BankManager with a password.
     * Update this new BankManager and password into the global information
     * @param totalManagerNum total number of BankManager created.
     * @param bankManagerMap a mapping from BankManager ID to the BankManager with that ID
     * @param newPassword the new password
     * @param passwordMap a mapping from BankIdentity ID to the encrypted password
     */
    public BankManager(int totalManagerNum, Map<String, BankManager> bankManagerMap,
                       String newPassword, Map<String, String> passwordMap) {
        this.id = "510" + (totalManagerNum + 1);
        bankManagerMap.put(id, this);
        setPassword(newPassword, passwordMap);
    }

    /**
     * Set the password and update this password into global information
     * @param newPassword the new password
     * @param passwordMap a mapping from BankIdentity ID to the encrypted password
     */
    private void setPassword(String newPassword, Map<String, String> passwordMap){
        PasswordManager pm = new PasswordManager(id);
        pm.setPassword(newPassword, passwordMap);
    }

    /**
     * Get the ID of the manager
     * @return the ID of the manager.
     */
    public String getId(){
        return id;
    }

    /**
     * Undo the most recent transaction for the Account
     * @param accNum the AccountNumber of the account which you want to undo transaction for
     * @throws ReverseNotPossibleException
     *  */
    public void undoAccRecentTrans(String accNum, TransactionManager trans, CashMachine machine, int times) throws
            NoTransactionException, ReverseNotPossibleException, NotCADBaseAccountException, NotEnoughMoneyException,
            CashNotWithdrawableException, TransactionAmountOverLimitException, NullPointerException{
//        try {
            for (int i = 1; i <= times; i++) {
                Transaction e = trans.popAccLastTrans(accNum).reverse();
                // try catch where pay bill can't be reversed.
                trans.makeTransaction(e, machine);
            }
//        } catch (EmptyStackException e) {
//            System.out.println("No more transaction related to this user.");
//        }// try catch if transaction cant be processed.
//        catch (ReverseNotPossibleException e) {
//            System.out.println("Impossible to undo this transaction.");
//        } catch (NoTransactionException e) {
//            System.out.println(e.getMessage());
//        }
    }


    /** Undo the most recent transaction for the user
     *
     * @param userId the UserId of the user which you want to undo transaction for
     * @throws ReverseNotPossibleException
     *  */
    public void undoUserRecentTrans(String userId, TransactionManager trans, CashMachine machine, int times) throws
            NoTransactionException, ReverseNotPossibleException, NotCADBaseAccountException, NotEnoughMoneyException,
            CashNotWithdrawableException, TransactionAmountOverLimitException, NullPointerException{
//        try {
            for (int i = 1; i <= times; i++) {
                Transaction e = trans.popUserLastTrans(userId).reverse();
                // try catch where pay bill can't be reversed.
                trans.makeTransaction(e, machine);
            }
//        } catch (EmptyStackException e) {
//            System.out.println("No more transaction related to this user.");
//        }// try catch if transaction can't be processed.
//         catch (ReverseNotPossibleException e) {
//            System.out.println("Impossible to undo this transaction.");
//        } catch (NoTransactionException e) {
//            System.out.println(e.getMessage());
//        }
    }

    /** Create a new Bank staff */
    public String createBankStaff(String type, InfoStorer infoStorer) {
        int Numuser = infoStorer.getStaffMap().size();
        Map<String, BankStaff> userMap = infoStorer.getStaffMap();
        Map<String, String> passwordMap = infoStorer.getPasswordMap();
        Map<String, Account>accountListMap = infoStorer.getAccountMap();
        BankStaff bankStaff = new BankStaff(Numuser);
        AccountCreator accCreator = new AccountCreator(bankStaff,infoStorer, type);
        userMap.put(bankStaff.getId(), bankStaff);
        PasswordManager passwordManager = new PasswordManager(bankStaff.getId());
        passwordManager.setPassword("1234", passwordMap);
        accCreator.createNewChequingAccount();
        return bankStaff.getId();
    }

    /**
     * The method for the BankEmployee to create new_user with default password "1234"
     * It will create a default primary chequing account
     * At the same time, all the information is going to be updated to global information
     */
    public String createUser(String type, InfoStorer infoStorer) {
        String userID = super.createUser(type, infoStorer);
        return userID;
    }

    /**
     * Restocking the CashMachine
     * @param machine the CashMachine storing cash
     * @param money a money object representing all the bills that need to be restocked into the machine
     * */
    public void restock(CashMachine machine, Money money) {
        super.restock(machine, money);
    }

   /** Add a user to a specific account to make that account joint.
    *
    *
    * */
   public void ShareAccount(AccountOwnable person, Account acc) throws UserNotOwnAccountException{
       acc.addOwner(person.getId());
       person.addAccount(acc);
   }
}

