package ATM.Accounts;

import ATM.Accounts.Plans.GICPlans.GICPlan;
import ATM.BankSystem.Date;
import ATM.GUI.Manager.ManagerCheckMachineBalance;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class GICAccount extends InvestmentAccount {

    private String accountNum;
    private String gicCode = "005";
    private double perspectTotalInterest;
    private double perspectiveBalance;
    private Currency balance;
    private Currency availableCredit;
    private int maturityYear;
    private int maturityMonth;
    private int maturityDay;
    private int termsOfMonth;
    private int monthLeft;
    private Currency principle;

    private boolean isEnd;
    // private double principle;

    private double penalty = 0.8;

    private GICPlan plan;


    public GICAccount(ArrayList<String> ownerID, int totalNumAcc, GICPlan s, double principle, String type) {
        super(ownerID, s);
        this.principle = new Currency(type,principle);
        this.accountNum = gicCode + (totalNumAcc + 1);
        this.plan = s;
        this.termsOfMonth = plan.getPeriodMonth();
        Period p = Period.ofMonths(plan.getPeriodMonth());

        LocalDate maturityDate = Date.getDate().getSystemCurrentTime().plus(p);

        this.maturityDay = maturityDate.getDayOfMonth();
        this.maturityMonth = maturityDate.getMonthValue();
        this.maturityYear = maturityDate.getYear();

        this.isEnd = plan.isEnd(maturityDate);
        // this.monthLeft = plan.getMonthLeft(getDateOfCreation(), isEnd);

        //perspectTotalInterest =
        //perspectiveBalance = plan.getCurrentPerspectBalance(principle,isEnd);

        balance = new Currency(type, principle);
        availableCredit = balance;
    }

    @Override
    public String getCurrencyType(){
        return balance.getType();
    }

    @Override
    public void compute(){
        if (!isEnd) {
            LocalDate maturityDate = LocalDate.of(maturityYear,maturityMonth,maturityDay);
            System.out.println(maturityDay);
            isEnd = plan.isEnd(maturityDate);
            if(isEnd){
                double interest = plan.compute(principle.getAmount());
                balance.setAmount(interest);
                System.out.println(balance);
                availableCredit = balance;
            }
//            perspectTotalInterest = plan.getCurrentPerspectTotalInterest(principle.getAmount(), isEnd);
//            perspectiveBalance = plan.getCurrentPerspectBalance(principle.getAmount(),isEnd);
//            balance.setAmount(perspectiveBalance);
//            if(!isEnd){
//            availableCredit.setAmount(principle.getAmount() + perspectTotalInterest * penalty);}
//            else {availableCredit = balance;}
        }
    }

    public boolean isEnd(){return isEnd;}

    public Currency getAvailableCredit(){
        return availableCredit;
    }


    public String getAccountNum(){
        return accountNum;
    }


    public Currency getBalance(){
        return availableCredit;
    }


    public void setBalance(double amount){
        this.principle.setAmount( amount);
    }


    public String getSummary(){return this.toString() + " , Remaining Balance: " + getBalance();}


    public Currency getNetBalance(){return balance;}

    @Override
    public String toString(){
        return this.getCurrencyType() + " GICAccount "+ this.accountNum + getBalance()+ "\n"
                + plan + "maturityYear"+ maturityYear +"/" + maturityMonth +"/" +maturityDay + "\n";
//                + monthLeft +" months left";
    }

    @Override
    public void transferOut(Currency amount){
        if(!isEnd){
            availableCredit.subtract(amount);
            balance = availableCredit;
            isEnd = true;
        }
        else{
            balance.subtract(amount);
            availableCredit = balance;
        }
    }
}