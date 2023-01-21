package model;

public class ExchangeRate {
    private final Currency currencyFrom;
    private final Currency currencyTo;
    private final double rate;

    public ExchangeRate(Currency currencyFrom, Currency currencyTo, Double rate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public double getRate() {
        return rate;
    }
}
