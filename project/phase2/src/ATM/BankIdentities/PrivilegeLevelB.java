package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Map;

public interface PrivilegeLevelB {
    public String createUser(String type, InfoStorer infoStorer);
    public void restock(CashMachine machine, Money money);
}
