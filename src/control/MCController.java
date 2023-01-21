package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import persistence.rest.ExchangeRateLoaderFromWS;
import view.swing.DialogSwing;

public class MCController implements ActionListener {
    private final DialogSwing dialog;
    private final ExchangeRateLoaderFromWS exchangeRateLoaderFromWS;

    public MCController(DialogSwing dialog,
            ExchangeRateLoaderFromWS exchangeRateLoaderFromWS) {
        this.dialog = dialog;
        this.exchangeRateLoaderFromWS = exchangeRateLoaderFromWS;
        this.dialog.registerController(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
       Money money = this.dialog.getMoney();
       Currency currencyFrom = money.getCurrency();
       Currency currencyTo = this.dialog.getCurrencyTo();
       ExchangeRate exchangeRate = this.exchangeRateLoaderFromWS.exchangeRateLoader(currencyFrom, currencyTo);
       dialog.refresh(new Money(exchangeRate.getRate() * money.getAmount(), currencyTo));
    }
}
