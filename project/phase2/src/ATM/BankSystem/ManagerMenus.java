package ATM.BankSystem;

import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.PasswordManager;
import ATM.Machine.Money;

public class ManagerMenus {

    private Typer typer = new Typer();

    /**Ask the manager for an password and verify it
     * If it goes through verification, the method direct manager to the manager main menu
     */
    public void managerLog(String id) {
        BankManager bankManager = BankSystem.getInfoManager().getBankManager(id);
        PasswordManager passwordManager = bankManager.getPassManager();
        System.out.println("Welcome, our bank manager~");
        String pw = typer.promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()) {
            pw = typer.promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        managerMainMenu(bankManager);
    }

    /**Displays the manager main menu to manager
     */
    public void printManagerMenu() {
        String[] list = {"Create User", "Undo Account's Most Recent Transaction",
                "Create an account for user", "Restock Cash Machine", "Reset Password", "Log out"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 7; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /**Ask the manager to select an action within the main menu*/
    public void managerMainMenu(BankManager bankManager) {
        while (bankManager.getPassManager().isLogin()) {
            printManagerMenu();
            String chosen = typer.ensureOption(1, 6);
            switch (chosen) {
                case "1":
                    //create a new user
                    bankManager.createUser();
                    break;
                case "2":
                    //undo the last transaction of a certain account
                    String accNum = typer.promptUser("Please enter an account number: ");
                    bankManager.undoMostRecentTrans(accNum);
                    break;
                case "3":
                    //create a new account
                    managerSubMenu(bankManager);
                    break;
                case "4":
                    //restock the ATM machine.
                    int numFive = typer.ensureInt("Please enter the amount of five dolloars you want to restock");
                    int numTen = typer.ensureInt("Please enter the amount of ten dolloars you want to restock");
                    int numTwenty = typer.ensureInt("Please enter the amount of twenty dolloars you want to restock");
                    int numFifty = typer.ensureInt("Please enter the amount of fifty dolloars you want to restock");
                    Money m = new Money(numFive, numTen, numTwenty, numFifty);
                    bankManager.restock(BankSystem.getInfoManager().getCashMachine(), m);
                    break;
                case "5":
                    String newPass = typer.promptUser("Please enter new password: ");
                    bankManager.getPassManager().setPassword(newPass);
                    break;
                case "6":
                    //log out
                    bankManager.getPassManager().logout();
            }
        }
    }

    /**Display the account sub menu to allow for selection */
    public void printManagerSubMenu() {
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
        String chosen = typer.ensureOption(1, 5);
        if (chosen.equals("5")) {
            stay = false;
        }
        if (stay) {
            String userID = typer.ensureID();
            switch (chosen) {
                case "1":
                    bankManager.createNewChequingAccount(userID);
                    break;
                case "2":
                    bankManager.createNewSavingAccount(userID);
                    break;
                case "3":
                    String limit = typer.ensureDouble("Please enter an account limit: ");
                    bankManager.createNewCreditAccount(userID, Double.valueOf(limit));
                    break;
                case "4":
                    String limitN = typer.ensureDouble("Please enter an account limit: ");
                    bankManager.createNewLineOfCredit(userID, Double.valueOf(limitN));
                    break;
            }
        }
    }

}
