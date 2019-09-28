package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;
import ATM.Transactions.ReverseNotPossibleException;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;
import ATM.InfoHandling.InfoManager;

import java.sql.SQLOutput;


/** BankManger class */
public class BankManager extends BankIdentity {
    private final String id;
    private PasswordManager manager;

    /** Creates a new BankManager with a password. Print its id and password out.
     *
     * @param password the password of the bankManager.
     * */
    public BankManager(String password) {
        this.id = "510" + (InfoManager.getInfoManager().getBankManagerNum() + 1);
        manager = new PasswordManager(this.id);
        manager.setPassword(password);
        //InfoManager.getInfoManager().add(this);
        System.out.println("Bank Manager ID: " + this.id + " , Password: " + password);
    }

    /** get the PassManager
     *
     * @return the password manager for manipulate the password
     * */
    public PasswordManager getPassManager(){
        return manager;
    }

    /** get the private ID of the manager
     *
     * @return the ID of the manager.
     *  */
    public String getId(){
        return id;
    }


    /**
     * The method for the BankManger to create new_user, user_accounts_Manager
     * with new accounts.
     * And BankManger could init the PassWord of the user with "1234".
     * */
    public void createUser() {
        /*
        need to add to the loader list.
         */
        User u = new User();
        UserAccManager accM = new UserAccManager(u.getId());
        u.setAccManager(accM);
        PasswordManager passM = new PasswordManager(u.getId());
        passM.setPassword("1234");
        u.setPassManager(passM);
        System.out.println("New user created! user ID: " + u.getId()
                + " initial Password: " + "1234");
        InfoManager.getInfoManager().add(u);
        passM.addObserver(InfoManager.getInfoManager());
        createNewChequingAccount(u.getId());


    }

    /** Restocking the CashMachine
     *
     * @param machine the CashMachine storing cash
     * @param money the money object
     * */
    public void restock(CashMachine machine, Money money) {
        machine.setAmount(money);
        // how to prevent other identities from touching cash machine setter?
    }

    /** Undo the most recent transaction for the Account
     *
     * @param accNum the AccountNumber of the account which you want to undo
     * @throws ReverseNotPossibleException
     *  */
    public void undoMostRecentTrans(String accNum) {
        try {
            Transaction e = InfoManager.getInfoManager().getTransactionManager().getAccLastTrans(accNum).reverse();
            // try catch where pay bill can't be reversed.
            TransactionManager manager = InfoManager.getInfoManager().getTransactionManager();
            manager.makeTransaction(e);
            if (e.isHappened()) {
                manager.addTrans(e);
            }
            // try catch if transaction cant be processed.
        } catch (ReverseNotPossibleException e) {
            System.out.println("Impossible to undo this transaction.");
        }
    }

    /** Create New Chequing Account for the User
     *
     * @param userID the userID of the user
     * @throws AlreadyPrimaryException
     *  */
    public void createNewChequingAccount(String userID) {
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m = u.getAccManager();
        ChequingAccount acc = new ChequingAccount(userID);
        if (m.getPrimaryChq() == null){
            try {
                m.setPrimaryChq(acc);
            } catch(AlreadyPrimaryException e ){
                System.out.println();
            }
        }
        m.addAccount(acc);


    }

    /** Create New Saving Account for the User
     *
     * @param userID the userID of the user
     *  */
    public void createNewSavingAccount(String userID){
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m = u.getAccManager();
        MonthlyInterest interest = new MonthlyInterest(0.01);
        SavingAccount acc = new SavingAccount(userID, interest);
        m.addAccount(acc);
    }


    /** Create New Credit Account for the User
     *
     * @param userID the userID of the user
     * @param limit the limit of the credit account
     *  */
    public void createNewCreditAccount(String userID, double limit){
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m = u.getAccManager();
        CreditAccount acc = new CreditAccount(userID, limit);
        m.addAccount(acc);
    }

    /** Create New Line of Credit Account for the User
     *
     * @param userID the userID of the user
     * @param limit the limit of the line of credit account
     *  */
    public void createNewLineOfCredit(String userID, double limit){
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m =u.getAccManager();
        LineOfCredit acc = new LineOfCredit(userID, limit);
        m.addAccount(acc);
    }
}

