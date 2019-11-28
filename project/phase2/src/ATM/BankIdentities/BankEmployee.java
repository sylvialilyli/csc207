package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;

import java.util.ArrayList;
import java.util.Map;

/** A class that represent a bank employee */
public abstract class BankEmployee extends BankIdentity implements  PrivilegeLevelB{

    public abstract String getId();

    /**
     * The method for the BankEmployee to create new_user with default password "1234"
     * It will create a default primary chequing account
     * At the same time, all the information is going to be updated to global information
     */
    public String createUser(String type, InfoStorer infoStorer) {
        Map<String, User> userMap = infoStorer.getUserMap();
        Map<String, String> passwordMap = infoStorer.getPasswordMap();
        int Numuser = infoStorer.getUserMap().size();
        User u = new User(Numuser);
        AccountCreator accCreator = new AccountCreator(u, infoStorer, type);
        userMap.put(u.getId(), u);
        PasswordManager passwordManager = new PasswordManager(u.getId());
        passwordManager.setPassword("1234", passwordMap);
        accCreator.createNewChequingAccount();
        return u.getId();
    }

    /**
     * Restocking the CashMachine
     * @param machine the CashMachine storing cash
     * @param money a money object representing all the bills that need to be restocked into the machine
     * */
    public void restock(CashMachine machine, Money money) {
        machine.setAmount(money);
        // how to prevent other identities from touching cash machine setter?
    }


}
