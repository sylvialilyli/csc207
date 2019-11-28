package ATM.Transactions;

import ATM.AccountTypeChecker.CADBaseChecker;
import ATM.Accounts.Account;
import ATM.Accounts.Currency;
import ATM.Accounts.TransferTypes.*;
import ATM.Machine.CashMachine;
import ATM.Machine.CashNotWithdrawableException;
import ATM.Machine.NotEnoughMoneyException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/** A class that manage all the transaction for one user TransactionManager class */
public class TransactionManager implements Serializable {

    /**
     * accTransMap record history of Transaction of specific accounts.
     */
    private Map<String, Stack<Transaction>> accTransMap = new HashMap<>();

    /**
     * userTransMap record history of Transaction of specific users.
     */
    private Map<String, Stack<Transaction>> userTransMap = new HashMap<>();

    /**
     * Create new instance of TransactionManager given two global transaction maps
     * @param accTransMap The global map that stores all transaction history under every account
     * @param userTransMap The global map that stores all transaction history under every user
     */
    public TransactionManager(Map<String, Stack<Transaction>> accTransMap, Map<String, Stack<Transaction>> userTransMap){
        this.accTransMap = accTransMap;
        this.userTransMap = userTransMap;
    }

    /**
     * Take in a map recording the request from user, and make corresponding transaction.
     * @param map the map recorded user's request
     * @return a corresponding new Transaction
     */
    public Transaction makeTransaction(Map<String, Object> map, CashMachine machine) throws NotCADBaseAccountException,
    NotEnoughMoneyException, CashNotWithdrawableException, TransactionAmountOverLimitException, NullPointerException{
        Transaction e = null;
//        try{
        switch((String)map.get("Type")) {
            case "Deposit":
                e = new Deposit((Depositable) map.get("toAccount"), (Currency)map.get("amount"));
                break;
            case "PayBill":
                e = new PayBill((Payable) map.get("fromAccount"), (String)map.get("to"),
                        (Currency) map.get("amount"));
                break;
            case "Withdrawal":
                e = new Withdrawal((Withdrawable) map.get("fromAccount"), (Currency) map.get("amount"));
                break;
            case "Regular":
                e = new RegularTrans((TransferOutable)map.get("fromAccount"),
                        (TransferInable)map.get("toAccount"), (Currency)map.get("amount"));
                break;
        }
        return makeTransaction(e, machine);
    }

    /**
     * Overloading method. Catch possible Exceptions when making a transaction.
     * @param e Transaction
     * @return Transaction
     */
    public Transaction makeTransaction(Transaction e, CashMachine machine) throws NotCADBaseAccountException,
            NotEnoughMoneyException, CashNotWithdrawableException, TransactionAmountOverLimitException, NullPointerException{
//        try{
            e.possibleToBegin();
            if (e instanceof Deposit) {
                checkCADBase(e.getToAcc());}
            else if (e instanceof Withdrawal) {
                checkCADBase(e.getFromAcc());
                machine.withdrawCash((int)(((Withdrawal)e).getAmount().getAmount()));
            }
            e.begin();
            if (e.isHappened()) {
                addTrans(e);
            }
//        } catch (CashNotWithdrawableException | NotEnoughMoneyException a){
//            System.out.println(a.getMessage());
//        } catch (TransactionAmountOverLimitException b) {
//            System.out.println("Not enough balance to complete transaction.");
//        } catch (NullPointerException c) {
//            System.out.println("Transaction is not possible.");
//        } catch (NotCADBaseAccountException e1){
//            System.out.println("The currency base of account chosen should be CAD");
//        }

        return e;
    }

    private void checkCADBase(Account acc) throws NotCADBaseAccountException {
        CADBaseChecker checker = new CADBaseChecker();
        if (!(checker.check(acc))){
            throw new NotCADBaseAccountException();
        }
    }

    /**
     * Return the most recent transaction of a certain user.
     * @param userId id of the user
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction viewUserLastTrans(String userId) throws NoTransactionException{
        if (!userTransMap.containsKey(userId)) {
            throw new NoTransactionException("No transaction with this user.");
        }
        Transaction e = userTransMap.get(userId).peek();
        return e;
    }

    /**
     * Get the most recent transaction of a certain user.(without putting it back to the list)
     * @param userId id of the user
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction popUserLastTrans(String userId) throws NoTransactionException {
        if (!userTransMap.containsKey(userId)) {
            throw new NoTransactionException("No transaction with this user.");
        }
        Transaction e = userTransMap.get(userId).pop();
        return e;
    }

    /**
     * Return the most recent transaction of a certain account.
     * @param accNum account number of the account
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction viewAccLastTrans(String accNum) throws NoTransactionException {
        if (!accTransMap.containsKey(accNum)) {
            throw new NoTransactionException("No transaction on this account.");
        }
        Transaction e = accTransMap.get(accNum).peek();
        return e;
    }

    /**
     * Get the most recent transaction of a certain account.(without putting it back to the list)
     * @param accNum account number of the account
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction popAccLastTrans(String accNum) throws NoTransactionException {
        if (!accTransMap.containsKey(accNum)) {
            throw new NoTransactionException("No transaction on this account.");
        }
        Transaction e = accTransMap.get(accNum).pop();
        return e;
    }

    private void accAddHelper(String accNum, Transaction trans) {
        if (accTransMap.containsKey(accNum)) {
            accTransMap.get(accNum).add(trans);
        } else {
            Stack a = new Stack<Transaction>();
            a.add(trans);
            accTransMap.put(accNum, a);
        }
    }

    private void userAddHelper(ArrayList<String> userId, Transaction trans) {
        for (String id: userId) {
            if (userTransMap.containsKey(id)) {
                userTransMap.get(id).add(trans);
            } else {
                Stack a = new Stack<Transaction>();
                a.add(trans);
                userTransMap.put(id, a);
            }
        }
    }

    /**
     * Add a transaction to history of transaction.
     * @param trans the Transaction to be added.
     */
    public void addTrans(Transaction trans){
        ArrayList<String> userId;
        String accNum;
        if (trans.getFromAcc() == null) {
            userId = trans.getToAcc().getOwnerID();
            accNum = trans.getToAcc().getAccountNum();
        } else {
            userId = trans.getFromAcc().getOwnerID();
            accNum = trans.getFromAcc().getAccountNum();
        }
        userAddHelper(userId, trans);
        accAddHelper(accNum, trans);
    }
}
