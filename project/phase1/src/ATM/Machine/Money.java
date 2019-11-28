package ATM.Machine;

/***
 * Money class
 */
public class Money {
    private int numFive;
    private int numTen;
    private int numTwenty;
    private int numFifty;

    /***
     * Construct a new Money
     * @param numFive (integer) number of $5
     * @param numTen (integer) number of $10
     * @param numTwenty (integer) number of $20
     * @param numFifty (integer) number of $50
     */
    public Money(int numFive, int numTen, int numTwenty, int numFifty) {
        this.numFive = numFive;
        this.numTen = numTen;
        this.numTwenty = numTwenty;
        this.numFifty = numFifty;
    }

    /***
     * Get the number of $5
     * @return int
     */
    public int getNumFive() {
        return numFive;
    }

    /***
     * Get the number of $10
     * @return int
     */
    public int getNumTen() {
        return numTen;
    }

    /***
     * Get the number of $20
     * @return int
     */
    public int getNumTwenty() {
        return numTwenty;
    }

    /***
     * Get the number of $50
     * @return int
     */
    public int getNumFifty() {
        return numFifty;
    }
}
