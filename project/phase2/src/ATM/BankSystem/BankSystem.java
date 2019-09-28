package ATM.BankSystem;

import ATM.AccountTypeChecker.*;
import ATM.Accounts.*;
import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.Money;

import java.util.*;

/**A simulation of the banking system */
public class BankSystem {

    /**A boolean that indicates the system is turned on */
    private static boolean SystemOn = true;

    /**A singleton infoManager where are the information is handled
     * Here we deserialize all the information
     */
    //private static InfoManager infoManager = InfoManager.getInfoManager();
    private static InfoManager infoManager = InfoManager.getInfoManager();


    public static void main(String[] args) {
        // In case this is the first time that the system has ever runs (never met any user or manager before)
        // We set a default manager into the system
        BankSystem bs = new BankSystem();
        ManagerMenus mm = new ManagerMenus();
        UserMenus um = new UserMenus();
        Typer keyboard = new Typer();
        if (infoManager.getBankManagerNum() == 0) {
            BankManager defaultManager = new BankManager("1234");
            //infoManager.add(defaultManager);
        }
        while (SystemOn) {
            bs.identityLog(mm, um, keyboard);
        }
    }


    /**Ask whichever bankIdentity is using the system for an ID
     * identify them as User or Manager
     * and direct them to different login mechanism for checking password
     */
    public void identityLog(ManagerMenus mm, UserMenus um, Typer keyboard) {
        boolean isFound = false;
        while (!isFound) {
            String id = keyboard.promptUser("Please enter your ID: ");
            if (infoManager.getInfoStorer().getBankManagerMap().containsKey(id)) {
                mm.managerLog(id);
                isFound = true;
            } else if (infoManager.getInfoStorer().getUserMap().containsKey(id)) {
                um.userLog(id);
                isFound = true;

            }
        }
    }

    public static InfoManager getInfoManager() {
        return infoManager;
    }
}

