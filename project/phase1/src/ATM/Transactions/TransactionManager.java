package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;
import ATM.Accounts.TransferOutable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * TransactionManager class
 */
public class TransactionManager implements Serializable {
    private static TransactionManager m;
    /***
     * accTransList record history of Transaction of specific accounts.
     */
    private Map<String, Stack<Transaction>> accTransList = new HashMap<>();
    /***
     * userTransList record history of Transaction of specific users.
     */
    private Map<String, Stack<Transaction>> userTransList = new HashMap<>();


    private TransactionManager(){}

    /***
     * Singleton pattern. get the only TransactionManager.
     * If there is not one, create a new TransactionManager.
     * @return the only TransactionManger
     */
    public static TransactionManager getTransactionManager() {
        if (m == null) {
            m = new TransactionManager();
        }
        return m;
    }

    /***
     * Take in a map recording the request from user, and make
     * corresponding transaction.
     * @param map the map recorded user's request
     * @return a corresponding new Transaction
     */
    public Transaction makeTransaction(Map<String, Object> map) {
        Transaction e = null;
        switch((String)map.get("Type")) {
            case "Deposit":
                e = new Deposit((Account)map.get("toAccount"), (Double)map.get("amount"));
                break;
            case "PayBill":
                e = new PayBill((Account)map.get("fromAccount"), (String)map.get("to"),
                        (Double)map.get("amount"));
                break;
            case "Withdrawal":
                e = new Withdrawal((Account)map.get("fromAccount"), (Double)map.get("amount"));
                break;
            case "Regular":
                e = new RegularTrans((TransferOutable)map.get("fromAccount"),
                        (TransferInable)map.get("toAccount"), (Double)map.get("Amount"));
                break;
        }
        return makeTransaction(e);
    }

    /***
     * Overrloading method. Catch possible Exceptions when making a transaction.
     * @param e Transaction
     * @return Transaction
     */
    public Transaction makeTransaction(Transaction e) {
        try{
            e.begin();
        } catch (TransactionAmountOverLimitException a) {
            System.out.println("Not enough balance to complete transaction.");
        } catch (NullPointerException b) {
            System.out.println("Transaction is not possible.");
        }
        return e;
    }

    /***
     * Return the most recent transaction of a certain user.
     * @param userId id of the user
     * @return the most recent Transaction
     */
    public Transaction getUserLastTrans(String userId) {
        Transaction e = userTransList.get(userId).pop();
        addTrans(e);
        return e;
    }

    /***
     * Return the most recent transaction of a certain account.
     * @param accNum account number of the account
     * @return the most recent Transaction
     */
    public Transaction getAccLastTrans(String accNum) {
        Transaction e = accTransList.get(accNum).pop();
        addTrans(e);
        return e;
    }

    private void helper(String userId, String accNum, Transaction trans) {
        accTransList.get(accNum).add(trans);
        userTransList.get(userId).add(trans);
    }

    /***
     * Add a transaction to history of transaction.
     * @param trans the Transaction to be added.
     */
    public void addTrans(Transaction trans){
        if (trans.getFromAcc() == null) {
            String userId = trans.getToAcc().getOwnerID();
            String accNum = trans.getToAcc().getAccountNum();
            helper(userId, accNum, trans);
        } else {
            String userId = trans.getFromAcc().getOwnerID();
            String accNum = trans.getFromAcc().getAccountNum();
            helper(userId, accNum, trans);

        }
    }
}
