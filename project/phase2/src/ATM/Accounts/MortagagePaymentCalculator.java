package ATM.Accounts;

public class MortagagePaymentCalculator {
    private double interestingRate = 0.065;
    private double monthLyRate = interestingRate/12.0;
    private int termOfMonth;
    private double monthlyPayment;
    private double loan;

    public MortagagePaymentCalculator(double loan, int termsOfyear, double interestingRate) {
        this.interestingRate = interestingRate;
        this.termOfMonth = termsOfyear*12;
        this.loan = loan;
        this.monthlyPayment = (this.loan * monthLyRate) / (1-Math.pow(1+ monthLyRate, - termOfMonth));
    }

    public double getMonthlyPayment(){
        return getMonthlyPayment();
    }

    public void setMonthLyRate(double newRate){
        this.monthLyRate = newRate;
    }
}
