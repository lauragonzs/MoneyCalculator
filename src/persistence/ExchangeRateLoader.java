package persistence;

import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateLoader {
    public ExchangeRate exchangeRateLoader(Currency currencyFrom, Currency currencyTo);
}