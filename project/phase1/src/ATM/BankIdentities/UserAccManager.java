package ATM.BankIdentities;
import ATM.Accounts.*;
import ATM.InfoHandling.InfoManager;

import java.io.Serializable;
import java.util.*;

public class UserAccManager implements Serializable {
    /**
    A map that have account type as key and an arraylist of Accounts that fit the type of key.
     <"accountType",<Accounts>>
     */
    private Map<String, ArrayList<Account>> listOfAcc = new HashMap<>();
    private String ownedUserId;
    private ChequingAccount primaryChq;

    /** Create a new userAccountManager
     *
     * @param ownedUserId the Id of the user of the userAccount manager*/
    public UserAccManager(String ownedUserId) {

        this.ownedUserId = ownedUserId;
        listOfAcc.put("TransferOutable", new ArrayList<>());
        listOfAcc.put("TransferInable",new ArrayList<>());
    }

    /** Adding the accounts in the Account Manager
     *
     * @param acc the account be added*/
    public void addAccount(Account acc){
        Class c = acc.getClass();
        String name = c.getName();
        if (listOfAcc.containsKey(name)) {
            listOfAcc.get(name).add(acc);
        } else {
            ArrayList<Account> list = new ArrayList<>();
            list.add(acc);
            listOfAcc.put(name, list);
        }
        if (acc instanceof TransferOutable) {
            listOfAcc.get("TransferOutable").add(acc);
        }
        listOfAcc.get("TransferInable").add(acc);
        InfoManager.getInfoManager().add(acc);
    }
    /** Use the account number to get the account
     *
     * @param accNum the accNum of the account we want to find
     * @throws NoSuchAccountException*/
    public Account getAccount(String accNum) throws NoSuchAccountException{
        Account result = null;
        for (ArrayList<Account> list: listOfAcc.values()) {
            for (Account acc: list) {
                if (acc.getAccountNum().equals(accNum)) {
                    result = acc;
                    break;
                }
            }
        }
        if (result == null) {
            throw new NoSuchAccountException("Can not find this account!");
        }
        return result;
    }


    /** get all the accounts
     * @return  all the account of the userAccManager*/
    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> all = new ArrayList<>();
        for (ArrayList<Account> list: listOfAcc.values()) {
            all.addAll(list);
        }
        return all;
    }

    /** Return a Arraylist of account that is a specific type
     *
     * @param type (type of account)
     * @return  ArrayList of account
     * @throws NoSuchTypeException
     */
    public ArrayList<Account> getTypeAccounts(String type) throws NoSuchTypeException{
        if (!(listOfAcc.keySet().contains(type))) {
            throw new NoSuchTypeException("You entered a wrong type!");
        }
        return listOfAcc.get(type);
    }

    /** Set the primary chequing account
     *
     * @param acc the account we want to set as primary
     * @throws AlreadyPrimaryException
     * */
    public void setPrimaryChq(Account acc) throws AlreadyPrimaryException{
        if (acc instanceof ChequingAccount) {
            if (acc.getOwnerID().equals(ownedUserId)) {
                if (acc == getPrimaryChq()) {
                    throw new AlreadyPrimaryException("This account is already " +
                            "a primary chequing account.");
                } else {
                    this.primaryChq = ((ChequingAccount)acc);
                }
            }
        }
    }

    /** Get primary chequing account */
    public ChequingAccount getPrimaryChq(){
        return primaryChq;
    }

    /** get the date of cration of the user acc manager
     * @param  accNum the account number*/
    public String getDateOfCreation(String accNum){
        String result = "";
        try {
            Account a = getAccount(accNum);
            result = a.getDateOfCreation().toString();
        }catch (NoSuchAccountException e) {
            System.out.println("There is no such account");
        }
        return result;
    }

    /** get the summary of all the account under user account manager */
    public String getSummary(){
        StringBuilder result = new StringBuilder();
        for (String s: listOfAcc.keySet()) {
            String name = String.join(" ", s.split("(?==\\p{Upper})"));
            String hey = name.replace("ATM.Account.", "");
            result.append(hey).append(":\n");
            ArrayList<Account> list = listOfAcc.get(s);
            for (Account acc: list) {
                result.append(acc.getSummary()).append("\n");
            }
        }
        return result.toString();
    }

    /** Get the net total balance */
    public int getNetTotal(){
        int net = 0;
        for (ArrayList<Account> list: listOfAcc.values()) {
            for (Account acc: list) {
                if (acc instanceof DebtAccount) {
                    net -= acc.getBalance();
                } else if (acc instanceof AssetAccount) {
                    net += acc.getBalance();
                }
            }
        }
        return net;
    }

}
