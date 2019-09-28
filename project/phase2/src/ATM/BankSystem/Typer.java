package ATM.BankSystem;

import ATM.InfoHandling.InfoManager;

import java.util.Scanner;

/***
 * A class containing method used to collect keyboard input
 */
public class Typer {

    /** ensure the customer entering the amount is the number is under two decimal places(the format of double)*/
    public String ensureDouble(String prompt) {
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
        return input;
    }

    /** ensure the customer entered information is integer*/
    public int ensureInt(String prompt) {
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
    public String ensureID() {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser("Please enter a user ID: ");
            if (InfoManager.getInfoManager().getInfoStorer().getUserMap().containsKey(input)) {
                isEnsured = true;
            } else {
                System.out.println("You did not enter a existing user id!");
            }
        }
        return input;
    }


    /** Ensure the customer entered the correct format of passwords: 4 digits of integer*/
    public String ensurePassword(int length) {
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

    /** Ensure the customer entered the valid option.
     * [i.e. the number entering should be limited by the available range]*/
    public String ensureOption(int min, int max) {
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

    /** Read and return the entering string by the customer*/
    public String promptUser(String prompt) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(prompt);
        String input = keyboard.nextLine();
        return input;
    }
}
