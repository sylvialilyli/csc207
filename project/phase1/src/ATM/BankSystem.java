package ATM;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        if (infoManager.getBankManagerNum() == 0) {
            BankManager defaultManager = new BankManager("1234");
            infoManager.add(defaultManager);
        }
        while (SystemOn) {
            bs.identityLog();
        }
    }


    /**Ask whichever bankIdentity is using the system for an ID
     * identify them as User or Manager
     * and direct them to different login mechanism for checking password
     */
    public void identityLog() {
        boolean isFound = false;
        while (!isFound) {
            String id = promptUser("Please enter your ID: ");
            if (infoManager.getInfoStorer().getBankManagerMap().containsKey(id)) {
                managerLog(id);
                isFound = true;
            } else if (infoManager.getInfoStorer().getUserMap().containsKey(id)) {
                userLog(id);
                isFound = true;

            }
        }
    }

    /**Ask the manager for an password and verify it
     * If it goes through verification, the method direct manager to the manager main menu
     */
    public void managerLog(String id) {
        BankManager bankManager = infoManager.getBankManager(id);
        PasswordManager passwordManager = bankManager.getPassManager();
        System.out.println("Welcome, our bank manager~");
        String pw = promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()) {
            pw = promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        managerMainMenu(bankManager);
    }

    /**Ask the user for an password and verify it
     * If it goes through verification, the method direct user to the user main menu*/
    public void userLog(String id) {
        User user = infoManager.getUser(id);
        PasswordManager passwordManager = user.getPassManager();
        System.out.println("Welcome, our customer~");
        String pw = promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()) {
            pw = promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        userMainMenu(user);
    }

    /**Displays the manager main menu to manager
     */
    public void printManagerMenu() {
        String[] list = {"Create User", "Undo Account's Most Recent Transaction",
                "Create an account for user", "Restock Cash Machine", "Log out"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 6; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /**Ask the manager to select an action within the main menu*/
    public void managerMainMenu(BankManager bankManager) {
        while (bankManager.getPassManager().isLogin()) {
            printManagerMenu();
            String chosen = ensureOption(1, 5);
            switch (chosen) {
                case "1":
                    //create a new user
                    bankManager.createUser();
                    break;
                case "2":
                    //undo the last transaction of a certain account
                    String accNum = promptUser("Please enter an account number: ");
                    bankManager.undoMostRecentTrans(accNum);
                    break;
                case "3":
                    //create a new account
                    managerSubMenu(bankManager);
                    break;
                case "4":
                    //restock the ATM machine.
                    int numFive = ensureInt("Please enter the amount of five dolloars you want to restock");
                    int numTen = ensureInt("Please enter the amount of ten dolloars you want to restock");
                    int numTwenty = ensureInt("Please enter the amount of twenty dolloars you want to restock");
                    int numFifty = ensureInt("Please enter the amount of fifty dolloars you want to restock");
                    Money m = new Money(numFive, numTen, numTwenty, numFifty);
                    bankManager.restock(infoManager.getCashMachine(), m);
                    break;
                case "5":
                    //log out
                    bankManager.getPassManager().logout();
            }
        }
    }

    /**Display the account sub menu to allow for selection */
    private void printManagerSubMenu() {
        String[] list = {"Chequing Account", "Saving Account",
                "Credit Account", "Line of Credit Account", "Back to previous menu"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 6; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /**Sub menu for the case 3 in the manager main menu
     * Allow manager to create different types of account
     */
    private void managerSubMenu(BankManager bankManager) {
        printManagerSubMenu();
        boolean stay = true;
        String chosen = ensureOption(1, 5);
        if (chosen.equals("5")) {
            stay = false;
        }
        if (stay) {
            String userID = ensureID();
            switch (chosen) {
                case "1":
                    bankManager.createNewChequingAccount(userID);
                    break;
                case "2":
                    bankManager.createNewSavingAccount(userID);
                    break;
                case "3":
                    Double limit = ensureDouble("Please enter an account limit: ");
                    bankManager.createNewCreditAccount(userID, limit);
                    break;
                case "4":
                    Double limitN = ensureDouble("Please enter an account limit: ");
                    bankManager.createNewLineOfCredit(userID, limitN);
                    break;
            }
        }
    }

    /**Display the main menu for user */
    private void printUserMenu() {
        String[] list = {"Get All Accounts Summary", "See Net Total of Balance", "View Account",
                "Set Primary Account", "Make Transaction", "Request Creation of Account", "Reset Password", "Log Out"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 9; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);

    }

    /**Allow user to select different action to complete */
    public void userMainMenu(User user) {
        while (user.getPassManager().isLogin()) {
            UserAccManager userAccManager = user.getAccManager();
            printUserMenu();
            String chosen = ensureOption(1, 8);
            switch (chosen) {
                case "1":
                    //get a summary of all accounts of the user
                    System.out.println(userAccManager.getSummary());
                    break;
                case "2":
                    //get a Net total of your balance
                    System.out.println(userAccManager.getNetTotal());
                    break;
                case "3":
                    //enter to a new menu that have options of all types of accounts that you have
                    userAccountInfoSubMenu(userAccManager);
                    break;
                case "4":
                    //have a menu of options of all chequing accounts that user has,
                    //set one to primary chequing account
                    userPriChqSubMenu(userAccManager);
                    break;
                case "5":
                    //enter a transaction menu to make a transaction
                    userTransSubMenu(userAccManager);
                case "6":
                    userReqAccSubMenu(user);
                    break;
                case "7":
                    System.out.println("Enter a password:");
                    String password = ensurePassword(4);
                    user.getPassManager().setPassword(password);
                    break;
                case "8":
                    user.getPassManager().logout();
                    break;
            }
        }

    }

    /** print all the available choice between the accounts*/
    private void printAllAccountList(ArrayList<Account> list){
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < list.size(); i++) {
            s.append("Option " + i + " : " + list.get(i-1).toString() + "\n");
        }
        s.append("Option "+ (list.size()+1) + ": Back to previous menu");
        System.out.println(s);
    }


    /** 比上面那个少一个option */
    private void printTypeAccountList(ArrayList<Account> list){
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < list.size(); i++) {
            s.append("Option " + i + " : " + list.get(i-1).toString() + "\n");
        }
        System.out.println(s);
    }


    /** Print all the information under one account*/
    private void printAccountInfoSubSubMenu() {
        String[] list = {"View account balance", "View last transaction",
                "View date of creation", "Back to previous menu"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 4; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);

    }

    /** Using the recursion when to provide the print All Account list choice*/
    private void userAccountInfoSubMenu(UserAccManager uam) {
        ArrayList<Account> list = uam.getAllAccounts();
        printAllAccountList(list);
        boolean stay = true;
        String chosen = ensureOption(1, list.size()+1);
        if (chosen.equals(String.valueOf((list.size()+1)))) {
            stay = false;
        }
        if (stay) {
            Account acc = list.get(Integer.valueOf(chosen));
            userAccountInfoSubSubMenu(acc);
        }
    }

    /** Depend on the customer's choice show the information under their accounts*/
    private void userAccountInfoSubSubMenu(Account acc){
        printAccountInfoSubSubMenu();
        boolean stay = true;
        String chosen = ensureOption(1, 4);
        if (chosen.equals(String.valueOf(4))) {
            stay = false;
        }
        if (stay) {
            switch (chosen) {
                case "1":
                    System.out.println(acc.getBalance());
                    break;
                case "2":
                    System.out.println(infoManager.getTransactionManager().getAccLastTrans(acc.getAccountNum()));
                    break;
                case "3":
                    System.out.println(acc.getDateOfCreation());
                    break;
            }

        }
    }

    /** Print all the information under the chequing account*/
    private ArrayList printPriChqSubMenu(UserAccManager manager) {
        StringBuilder message = new StringBuilder();
        int length = 1;
        ArrayList acclist = new ArrayList();
        try {
            acclist = manager.getTypeAccounts("Chequing");
            length = manager.getTypeAccounts("Chequing").size();
        } catch (NoSuchTypeException e) {
            System.out.println("You do not have chequing account.");
        }
        for (int i = 1; i < length; i++) {
            message.append("Option").append(i).append(((ChequingAccount) acclist.get(i)).getAccountNum()).append("\n");
        }
        System.out.println(message);
        return acclist;
    }

    /** Manager setting the selected chequing account as primary*/
    private void userPriChqSubMenu(UserAccManager manager) {
       ArrayList acclist = printPriChqSubMenu(manager);
       String chosen = ensureOption(1, acclist.size());
       try{
        manager.setPrimaryChq((ChequingAccount)acclist.get(Integer.valueOf(chosen)));}
       catch (AlreadyPrimaryException e){
           System.out.println("This is already a primary account.");
       }
    }

    /** ? */
    private void printTransSubSubMenu() {
        String[] list = {"Continue", "Back to previous menu"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 2; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /** Print all the available transaction*/
    private void printTransSubMenu() {
        String[] list = {"Regular Transaction", "Deposit",
                "Withdrawal", "Pay Bills", "Back to previous menu"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /** Apply the transaction on each account*/
    private void userTransSubMenu(UserAccManager uam) {
        printTransSubMenu();
        boolean stay = true;
        String chosen = ensureOption(1, 5);
        if (chosen.equals(String.valueOf(5))) {
            stay = false;
        }
        if (stay) {
            switch (chosen) {
                case "1":
                    regularTransactionMenu(uam);
                    break;
                case "2":
                    depositMenu(uam);
                    break;
                case "3":
                    withdrawalMenu(uam);
                    break;
                case "4":
                    payBillMenu(uam);
                    break;
            }

        }
    }

    /** Apply pay bill feature under selected account*/
    private void payBillMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "PayBill");
        try{
            ArrayList<Account> depositList = uam.getTypeAccounts("TransferInable");
            map.put("fromAccount", transactionHelper(depositList));
        }
        catch(NoSuchTypeException e){
            System.out.println(e);
        }
        String to = promptUser("Please enter to whom: ");
        map.put("to", to);
        String amount = promptUser("Please enter amount: ");
        map.put("amount", amount);
        transactionHelperOne(map);
    }

    /** Apply withdraw under selected account*/
    private void withdrawalMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "Withdrawal");
        try{
            ArrayList<Account> withdrawalList = uam.getTypeAccounts("TransferInable");
            map.put("fromAccount", transactionHelper(withdrawalList));
        }
        catch(NoSuchTypeException e){
            System.out.println(e);
        }
        String amount = promptUser("Please enter amount: ");
        map.put("amount", amount);
        transactionHelperOne(map);
    }

    /** deposing the entering amount into the selected account*/
    private void depositMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "Deposit");
        try{
            ArrayList<Account> depositList = uam.getTypeAccounts("TransferInable");
            map.put("toAccount", transactionHelper(depositList));
        }
        catch(NoSuchTypeException e){
            System.out.println(e);
        }
        String amount = promptUser("Please enter amount: ");
        map.put("amount", amount);
        transactionHelperOne(map);
    }

    /** Apply the regular transactions between the selected accounts*/
    private void regularTransactionMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "Regular");
        try {
            ArrayList<Account> transferOutableList = uam.getTypeAccounts("TransferOutable");
            map.put("fromAccount", transactionHelper(transferOutableList));
            ArrayList<Account> transferInableList = uam.getTypeAccounts("TransferInable");
            map.put("toAccount", transactionHelper(transferInableList));
        } catch (NoSuchTypeException e) {
            System.out.println(e);
        }
        String amount = promptUser("Please enter amount: ");
        map.put("amount", amount);
        transactionHelperOne(map);
    }

    /** Users send their request for the creation of new account.*/
    private void userReqAccSubMenu(User user){
        printManagerSubMenu();
        String chosen = ensureOption(1, 6);
        switch (chosen){
            case "1":
                user.sendRequest("Chequing Account");
            case "2":
                user.sendRequest("Saving Account");
            case "3":
                user.sendRequest("Credit Account");
            case "4":
                user.sendRequest("Line of Credit");
        }
    }

    /** Recording the recording transaction information in infoManager*/
    private void transactionHelperOne(Map<String, Object> map){
        boolean stay = true;
        printTransSubSubMenu();
        String chosen = ensureOption(1, 2);
        if (chosen.equals("2")) {
            stay = false;
        }
        if (stay) {
            infoManager.getTransactionManager().makeTransaction(map);
        }
    }

    /** Helper function for the transaction: Aimed to generator the choices under the accounts*/
    private Account transactionHelper(ArrayList<Account> list) {
        printTypeAccountList(list);
        String chosen = ensureOption(1, list.size()+1);
        return list.get(Integer.valueOf(chosen));
    }

    //userReqAccSubMenu()


    /** ensure the customer entering the amount is the number is under two decimal places(the format of double)*/
    private Double ensureDouble(String prompt) {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser(prompt);
            try {
                Double d = Double.valueOf(input);
                isEnsured = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Please remember to enter a number.");
            }
        }
        return Double.valueOf(input);
    }

    /** ensure the customer entered information is integer*/
    private int ensureInt(String prompt) {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser(prompt);
            try {
                Integer i = Integer.valueOf(input);
                isEnsured = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Please remember to enter an integer.");
            }
        }
        return Integer.valueOf(input);
    }

    /** ensure the customer entered the valid User ID*/
    private String ensureID() {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser("Please enter a user ID: ");
            if (infoManager.getInfoStorer().getUserMap().containsKey(input)) {
                isEnsured = true;
            } else {
                System.out.println("You did not enter a existing user id!");
            }
        }
        return input;
    }

    /** Ensure the customer entered the correct format of passwords: 4 digits of integer*/
    private String ensurePassword(int length) {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser("Please enter a password (a 4 digit integer): ");
            if (input.length() == length) {
                try {
                    Integer i = Integer.valueOf(input);
                    isEnsured = true;
                } catch (NumberFormatException nfe) {
                    System.out.println("Please remember to enter a integer!");
                }
            } else {
                System.out.println("Please remember to enter a 4 digit integer!");
            }
        }
        return input;
    }

    /** Ensure the customer entered the valid operation.
     * [i.e. the number entering should be limited by the available range]*/
    private String ensureOption(int min, int max) {
        boolean isEnsured = false;
        String chose = "";
        while (!isEnsured) {
            chose = promptUser("Please enter an integer ranging from " + min + " to " + max + ":");
            try {
                Integer i = Integer.valueOf(chose);
                if (min <= i && i <= max) {
                    isEnsured = true;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please remember to enter an integer.");
            }
        }
        return chose;
    }

    /** Read the entering string by the customer*/
    private String promptUser(String prompt) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(prompt);
        String input = keyboard.nextLine();
        return input;
    }
}

