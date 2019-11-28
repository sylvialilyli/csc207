package ATM.Accounts;

import ATM.org.openexchangerates.oerjava.OpenExchangeRates;

import java.io.Serializable;
import java.math.BigDecimal;

public class Currency implements Serializable {

    private String type = "CAD";
    private double amount;

    public Currency(double amount){
        this.amount = amount;
    }

    public Currency(String type, double amount){
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addAmount(double amount){
        this.amount += amount;
    }

    public void add(Currency other){
        double rate = exchangeToBaseCurrency(other.getType(), this.getType());
        this.amount += other.getAmount() * rate;
    }

    public void subtract(Currency other){
        double rate = exchangeToBaseCurrency(other.getType(), this.getType());
        this.amount -= other.getAmount() * rate;
    }

    protected double exchangeToBaseCurrency(String billsCurrency,String baseCurrency){
        OpenExchangeRates oer = new OpenExchangeRates();
        double rateDecial = oer.getExchangeRate(billsCurrency, baseCurrency);
        return rateDecial;
    }
    public String toString(){
        return this.type + ": " + this.amount;
    }
}
