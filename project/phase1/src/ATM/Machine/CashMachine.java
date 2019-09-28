package ATM.Machine;

import ATM.InfoHandling.AlertWriter;

import java.io.Serializable;
import java.util.Arrays;


public class CashMachine implements Serializable {
    /***
     * Amounts of dominations the CashMachine has.
     */
    private int numFiveD;
    private int numTenD;
    private int numTwentyD;
    private int numFiftyD;
    private AlertWriter writer = new AlertWriter();

    public CashMachine(){}

    /***
     * Set the number of dominations in CashMachine.
     * @param money record of number of dominations.
     */
    public void setAmount(Money money) {
        numFiveD = money.getNumFive();
        numFiftyD = money.getNumFifty();
        numTenD = money.getNumTen();
        numTwentyD = money.getNumTwenty();
    }

    /***
     * Return a integer of number of $5.
     * @return int
     */
    public int getNumFiveD() {
        return numFiveD;
    }

    /***
     * Return a integer of number of $10.
     * @return int
     */
    public int getNumTenD() {
        return numTenD;
    }

    /***
     * Return a integer of number of $20.
     * @return int
     */
    public int getNumTwentyD() {
        return numTwentyD;
    }

    /***
     * Return a integer of number of $50.
     * @return int
     */
    public int getNumFiftyD() {
        return numFiftyD;
    }

    /***
     * Return amount of dominations stored in the CashMachine.
     * @return Money as record of dominations
     */
    public Money getAmount(){
        return new Money(getNumFiveD(), getNumTenD(), getNumTwentyD(),getNumFiftyD());
    }

    /***
     * Withdraw cash from the machine with a requested amount.
     * @param amount integer shows the amount of money needed to be withdrawn.
     * @throws CashNotWithdrawableException if the entered amount is not multiple of 5.
     * @throws NotEnoughMoneyException if the machine has not enough money to complete withdrawal.
     */
    public void withdrawCash(int amount) throws CashNotWithdrawableException, NotEnoughMoneyException{
        if (amount % 5 != 0) {
            throw new CashNotWithdrawableException("Amount entered should be multiple of 5.");
        }
        int[] Dbox = possibleD(amount);
        int[] DAmount = {getNumFiveD(), getNumTenD(), getNumTwentyD(), getNumFiftyD()};
        int[] Dnum = new int[Dbox.length];
        for (int i = (Dbox.length-1); i > 0; i--) {
            Dnum[i] = (amount/Dbox[i]);
            if ((DAmount[i] - Dnum[i]) >= 0) {
                amount -= (Dbox[i]*Dnum[i]);
            }
        }
        if (amount != 0) {
            throw new NotEnoughMoneyException("Not enough bills in the machine.");
        } else {
            int[] numCopy = Arrays.copyOf(Dnum, 4);
            Money m = new Money(DAmount[0] - numCopy[0], DAmount[1] - numCopy[1],
                    DAmount[2] - numCopy[2], DAmount[3] - numCopy[3]);
            setAmount(m);

        }
        checkAmount();

    }

    private int[] possibleD(int amount) {
        int[] doms = {5, 10, 20, 50};
        if (amount<50 && amount>=20) {
            int [] domie = Arrays.copyOf(doms, 3);
            doms = domie;
        } else if (amount < 20 && amount >= 10) {
            int [] domie = Arrays.copyOf(doms, 2);
            doms = domie;
        } else if (amount == 5) {
            int [] domie = Arrays.copyOf(doms, 1);
            doms = domie;
        }
        return doms;
    }

    /***
     * Check the current amount of dominations in the machine.
     * Call warning if the amount is under 20.
     */
    public void checkAmount() {
        Money m = getAmount();
        if (m.getNumFive() < 20) { warning("Five"); }
        if (m.getNumTen() < 20) { warning("Ten"); }
        if (m.getNumTwenty() < 20) { warning("Twenty"); }
        if (m.getNumFifty() < 20) { warning("Fifty"); }
    }

    /***
     * Send alert in order to ask BankManager to restock the machine
     * @param value the domination whose amount is under 20.
     */
    public void warning(String value){
        writer.write("Amount of "+ value + "dollar domination is under 20!");
    }
}
