package ATM.BankIdentities;
import ATM.AccountTypeChecker.TypeChecker;
import ATM.Accounts.*;
import ATM.InfoHandling.InfoManager;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.*;

public class UserAccManager implements Serializable,Iterable<Account>{
    /**
    A map that have account type as key and an arraylist of Accounts that fit the type of key.
     <"accountType",<Accounts>>
     */
    //private Map<String, ArrayList<Account>> listOfAcc = new HashMap<>();

    private ArrayList<Account> listOfAcc = new ArrayList<>();
    private String ownedUserId;
    private ChequingAccount primaryChq;

    /** Create a new userAccountManager
     *
     * @param ownedUserId the Id of the user of the userAccount manager*/
    public UserAccManager(String ownedUserId) {

        this.ownedUserId = ownedUserId;
    }

    /** Adding the accounts in the Account Manager
     *
     * @param acc the account be added*/
    public void addAccount(Account acc) throws UserNotOwnAccountExcetpiton {
        if (acc.getOwnerID().equals(ownedUserId)) {
            this.listOfAcc.add(acc);
            InfoManager.getInfoManager().add(acc);
        } else {
            throw new UserNotOwnAccountExcetpiton("This account is not owned by user " + ownedUserId);
        }
    }


    /** Use the account number to get the account
     *
     * @param accNum the accNum of the account we want to find
     * @throws NoSuchAccountException*/
    public Account getAccount(String accNum) throws NoSuchAccountException{
        Iterator<Account> i = iterator();
        Account acc = null;
        while (i.hasNext()) {
            acc = i.next();
            if (acc.getAccountNum().equals(accNum)) {
                break;
            }
        }
        if (acc == null) {
            throw new NoSuchAccountException("User "+ownedUserId+" has no such an account.");
        }
        return acc;
    }


    /** get all the accounts
     * @return  all the account of the userAccManager*/
    public ArrayList<Account> getListOfAcc() {
        return listOfAcc;
    }

    /** Return a Arraylist of account that is a specific type
     *
     * @param   checker to check if account is a specific type
     * @return  ArrayList of account
     */
    public ArrayList<Account> getTypeAccounts(TypeChecker checker) {
        ArrayList<Account> list = new ArrayList<>();
        for (Account acc: this) {
            if (checker.check(acc)){
                list.add(acc);
            }
        }
        return list;
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

    /** get the summary of all the account under user account manager */
    public String getSummary(){
        StringBuilder result = new StringBuilder();
        Map<String, ArrayList<Account>> map = summaryHelper();
        for (String s: map.keySet()) {
            result.append(s).append(":\n");
            for (Account acc: map.get(s)) {
                result.append(acc.getSummary()).append("\n");
            }
        }
        return result.toString();
    }

    private Map<String, ArrayList<Account>> summaryHelper(){
        Map<String, ArrayList<Account>> map = new HashMap<>();
        for (Account acc: this) {
            String className = acc.getClass().getName();
            String className1 = className.replace("ATM.Accounts.", "");
            String classNameMod = String.join(" ", className1.split("(?=\\p{Upper})"));
            if (map.containsKey(classNameMod)) {
                map.get(classNameMod).add(acc);
            } else {
                ArrayList<Account> list = new ArrayList<>();
                list.add(acc);
                map.put(classNameMod, list);}
        }
        return map;
    }

    /** Get the net total balance */
    public int getNetTotal(){
        int net = 0;
        for (Account acc: this) {
            if (acc instanceof DebtAccount) {
                net -= acc.getBalance();
            } else if (acc instanceof AssetAccount) {
                net += acc.getBalance();
            }
        }
        return net;
    }

    @Override
    public Iterator<Account> iterator() {
        return new AccountsIterator();
    }

    private class AccountsIterator implements Iterator<Account> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < listOfAcc.size();
        }

        @Override
        public Account next() {
            if (hasNext()) {
                return listOfAcc.get(i ++);
            }
            throw new NoSuchElementException();
        }
    }
}
