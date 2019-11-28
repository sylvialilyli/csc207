package ATM.BankIdentities;

import ATM.Machine.CashMachine;
import ATM.Machine.CashNotWithdrawableException;
import ATM.Machine.NotEnoughMoneyException;
import ATM.Transactions.*;

public interface PrivilegeLevelA extends PrivilegeLevelB{

     public void undoAccRecentTrans(String accNum, TransactionManager trans, CashMachine machine, int times) throws
             NoTransactionException, ReverseNotPossibleException, NotCADBaseAccountException, NotEnoughMoneyException,
             CashNotWithdrawableException, TransactionAmountOverLimitException, NullPointerException;
    public void undoUserRecentTrans(String userId, TransactionManager trans, CashMachine machine, int times) throws
            NoTransactionException, ReverseNotPossibleException, NotCADBaseAccountException, NotEnoughMoneyException,
            CashNotWithdrawableException, TransactionAmountOverLimitException, NullPointerException;


}
