package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.PasswordManager;
import ATM.BankIdentities.User;
import ATM.Machine.CashMachine;
import ATM.Transactions.TransactionManager;

import java.io.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Managing the saving and loading of all information stored in infoStorer
 * This is an observer of password manager to make sure infoStorer is serialized every time an user log out
 */
public class InfoManager implements Observer {

    /**The file path in which all the info is serialized and stored in*/
    private static String filePath = "./serializedinfo.ser";

    /**Where all the information is stored in */
    private static InfoStorer infoStorer;

    /**A private static InfoManager that we use as a singleton */
    private static InfoManager infoManager;

    /**
     * Creates a new InfoManager that obtains infoStorer from the file in filePath */
    private InfoManager(){
        infoStorer = new InfoStorer();
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            try {
                file.createNewFile();
            } catch (Exception ex) {
                System.out.println(ex);;
            }
        }
    }

    /** @return our singleton infoManager */
    public static InfoManager getInfoManager(){
        if (infoManager == null){
            infoManager = new InfoManager();
        }
        return infoManager;
    }

    /** A helper method that read the .ser file stored in path
     *  and deserialize the file into infoStorer
     *
     * @param path the path in which the .ser file is stored in
     */
    public void readFromFile(String path) {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the InfoStorer
            infoStorer = (InfoStorer) input.readObject();
            addRelationship();
            input.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /** Make sure an infoManager instance can observe all the PasswordManagers */
    private void addRelationship(){
        for (User user: getInfoStorer().getUserMap().values()){
            PasswordManager pw = user.getPassManager();
            pw.addObserver(this);
        }
    }

    /** Save and serialize the infoStorer into the .ser file at the filePath*/
    public void saveToFile() {
        try {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the InfoStorer
        output.writeObject(infoStorer);
        output.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /** @return our static infoStorer */
    public InfoStorer getInfoStorer() {
        return infoStorer;
    }

    /**@param id User ID
     * @return the User corresponding with ID id */
    public User getUser(String id){
        return infoStorer.getUserMap().get(id);
    }

    /**@param id Account number
     * @return the Account corresponding with Account number id */
    public Account getAccount(String id){
        return infoStorer.getAccountMap().get(id);
    }

    /**@param id BankManager ID
     * @return  the User corresponding with ID id */
    public BankManager getBankManager(String id){
        return infoStorer.getBankManagerMap().get(id);
    }

    /**@return TransactionManager: a singleton that is used to handle transaction */
    public TransactionManager getTransactionManager(){
        return getInfoStorer().getTransactionManager();
    }

    /**@return CashMachine */
    public CashMachine getCashMachine(){
        return getInfoStorer().getCashMachine();
    }

    /***
     * Get number of users already stored in infoStorer
     * @return int
     */
    public int getUserNum(){
        return infoStorer.getUserMap().size();
    }

    /***
     * Get number of account already stored in infoStorer.
     * @return int
     */
    public int getAccountNum(){
        return infoStorer.getAccountMap().size();
    }

    /***
     * Get number of bank managers already stored in infoStorer.
     * @return int
     */
    public int getBankManagerNum(){
        return infoStorer.getBankManagerMap().size();
    }

    /***
     * Add a new user to infoStorer.
     * @param newUser User to be added.
     */
    public void add(User newUser){
        infoStorer.addUser(newUser);
    }

    /***
     * Add a new account to infoStorer.
     * @param newAccount Account to be added.
     */
    public void add(Account newAccount){
        infoStorer.addAccount(newAccount);
    }

    /***
     * Add a new bank manager to infoStorer.
     * @param newBankManager BankManager to be added.
     */
    public void add(BankManager newBankManager){
        infoStorer.addBankManager(newBankManager);
    }

    /***
     * Add a new request of account creation to infoStorer.
     * @param userID id of user who sent request
     * @param type type of account requested
     */
    public void add(String userID, String type) { infoStorer.
    getAccountCreationRequest().put(userID, type);}

    /***
     * Remove a request of account creation from infoStorer.
     * @param userID id of user who sent request
     * @param type type of account requested
     */
    public void removeRequest(String userID, String type) { infoStorer.getAccountCreationRequest().remove(userID, type); }

    /***
     * Update method. Serialize infoStorer if it is called.
     * @param o Observable item which infoManager is observing
     * @param arg Object arugument
     */
    @Override
    public void update(Observable o, Object arg) {
        saveToFile();
    }
}

