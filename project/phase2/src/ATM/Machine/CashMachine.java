package ATM.Machine;

import ATM.InfoHandling.AlertWriter;

import java.io.Serializable;
import java.util.Arrays;

/** A class that represent a cash machine */
public class CashMachine implements Serializable {

    /**
     * Amounts of domination the CashMachine has.
     */
    private int numFiveD;
    private int numTenD;
    private int numTwentyD;
    private int numFiftyD;

    /** Create an instance of CashMachine */
    public CashMachine(){}

    /**
     * Set the number of domination in CashMachine with given money object
     * @param money record of number of domination.
     */
    public void setAmount(Money money) {
        numFiveD = money.getNumFive();
        numFiftyD = money.getNumFifty();
        numTenD = money.getNumTen();
        numTwentyD = money.getNumTwenty();
    }

    /**
     * Return the number of $5 bills in machine.
     * @return int
     */
    public int getNumFiveD() {
        return numFiveD;
    }

    /**
     * Return the number of $10 bills in machine.
     * @return int
     */
    public int getNumTenD() {
        return numTenD;
    }

    /**
     * Return the number of $20 bills in machine.
     * @return int
     */
    public int getNumTwentyD() {
        return numTwentyD;
    }

    /**
     * Return the number of $50 bills in machine.
     * @return int
     */
    public int getNumFiftyD() {
        return numFiftyD;
    }

    /**
     * Return amount of domination stored in the CashMachine.
     * @return Money as record of domination
     */
    public Money getAmount(){
        return new Money(getNumFiveD(), getNumTenD(), getNumTwentyD(),getNumFiftyD());
    }

    /**
     * Withdraw a requested amount of cash from the machine.
     * @param amount: An integer shows the amount of money needed to be withdrawn.
     * @throws CashNotWithdrawableException: When the entered amount is not multiple of 5.
     * @throws NotEnoughMoneyException: When the machine has not enough money to complete the withdrawal.
     */
    public void withdrawCash(double amount) throws CashNotWithdrawableException, NotEnoughMoneyException{
        int newAmount = (int)amount;
        if (newAmount % 5 != 0) {
            throw new CashNotWithdrawableException("Amount entered should be a multiple of 5.");
        }
        int[] Dbox = possibleD(newAmount);
        int[] DAmount = {getNumFiveD(), getNumTenD(), getNumTwentyD(), getNumFiftyD()};
        int[] Dnum = new int[Dbox.length];
        for (int i = (Dbox.length - 1); i >= 0; i--) {
            Dnum[i] = ((newAmount/Dbox[i]));
            if ((DAmount[i] - Dnum[i]) >= 0) {
                newAmount -= (Dbox[i]*Dnum[i]);
            }
        }
        if (newAmount != 0) {
            throw new NotEnoughMoneyException("Not enough bills in the machine.");
        } else {
            int[] numCopy = Arrays.copyOf(Dnum, 4);
            Money m = new Money(DAmount[0] - numCopy[0], DAmount[1] - numCopy[1],
                    DAmount[2] - numCopy[2], DAmount[3] - numCopy[3]);
            setAmount(m);
        }
        checkAmount();
    }

    /**
     * Helper function for withdrawCash
     * Return the possible domination of cash bills that the given amount can be divided in
     * @return int[] possible domination */
    private int[] possibleD(int amount) {
        int[] doms = {5, 10, 20, 50};
        if (amount < 50 && amount >= 20) {
            doms = Arrays.copyOf(doms, 3);
        } else if (amount < 20 && amount >= 10) {
            doms = Arrays.copyOf(doms, 2);
        } else if (amount == 5) {
            doms = Arrays.copyOf(doms, 1);
        }
        return doms;
    }

    /**
     * Check the current amount of domination in the machine.
     * Call warning if the amount of any cash bill is under 20.
     */
    public void checkAmount() {
        Money m = getAmount();
        if (m.getNumFive() < 20) { warning("Five"); }
        if (m.getNumTen() < 20) { warning("Ten"); }
        if (m.getNumTwenty() < 20) { warning("Twenty"); }
        if (m.getNumFifty() < 20) { warning("Fifty"); }
    }

    /**
     * Send an alert using AlertWriter to write a txt file to ask BankManager to restock the machine
     * @param value The name of cash bill whose amount is under 20.
     */
    public void warning(String value){
        AlertWriter writer = new AlertWriter();
        writer.write("Amount of "+ value + "dollar domination is under 20!");
    }
}
