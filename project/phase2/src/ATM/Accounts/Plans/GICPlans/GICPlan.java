package ATM.Accounts.Plans.GICPlans;

import ATM.Accounts.Plans.Plan;
import ATM.BankSystem.Date;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public abstract class GICPlan implements Plan, Serializable {

    private double interestRate;
    private int periodOfMonth;
    private LocalDate dateOfCreation = Date.getDate().getSystemCurrentTime();


    @Override
    public double compute(double amount){
        double newAmount = amount*(interestRate +1);
        return newAmount;
    }

    /** set the period of gic plan in months*/
    protected void setPeriodOfMonth(int periodOfMonth) {
        this.periodOfMonth = periodOfMonth;
    }

    /** get the totally interesting rate for all the periods*/
    public double getInterestRate() {
        return interestRate;
    }

    /** set the totally interesting rate for all the periods*/
    protected void setInterestRate(double newInterestRate){
        this.interestRate = newInterestRate;
    }

    /** get the totally interest for all the period*/
    public double computeTotalInterest(double amount){
        return amount*interestRate;
    }

    /** get the monthly interest for all the period*/
    public double computeMonthlyInterest(double amount){
        return compute(amount)/periodOfMonth;
    }

    public int getPeriodMonth(){return periodOfMonth;}

    public boolean isEnd(LocalDate maturityDate){
        LocalDate current = Date.getDate().getSystemCurrentTime();
        System.out.println("Run plan!");
        if(maturityDate.getYear() > current.getYear()){return true;}
        else if(maturityDate.getMonthValue() > current.getMonthValue()){return true;}
        else if (maturityDate.getDayOfMonth() > current.getDayOfMonth()){return true;}
        else {return false;}
    }

//    public double getCurrentPerspectTotalInterest(double amount, boolean isEnd){
//        if(!isEnd){
//            LocalDate current = Date.getDate().getSystemCurrentTime();
//            Period p = Period.between(current,dateOfCreation);
//            int month = p.getMonths();
//            int year = p.getYears();
//            int length = year * 12 + month;
//            return length * computeMonthlyInterest(amount);
//        }
//        else {return computeTotalInterest(amount);}
//    }

//    public double getCurrentPerspectBalance(double amount, boolean isEnd){
//        return amount + getCurrentPerspectTotalInterest(amount, isEnd);
//    }

//    public int getMonthLeft(LocalDate dateOfCreation, boolean isEnd){
//        if(!isEnd){
//            return periodOfMonth - getMonthpassed(dateOfCreation);
//        }
//        else{return 0;}
//    }
//
//
//    public int getMonthpassed(LocalDate dateOfCreation){
//        LocalDate current = Date.getDate().getSystemCurrentTime();
//        Period p = Period.between(dateOfCreation, current);
//        int years = p.getYears();
//        int months = p.getMonths();
//        return (years+1) *12 + months+1;
//    }

    public abstract String toString();
}