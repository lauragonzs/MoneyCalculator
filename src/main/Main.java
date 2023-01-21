package Main;

import control.MCController;
import java.util.List;
import javax.swing.SwingUtilities;
import model.Currency;
import persistence.files.CurrencyLoaderFromFile;
import persistence.rest.ExchangeRateLoaderFromWS;
import view.swing.DialogSwing;
import view.swing.MoneyCalculatorGUI;


public class Main {

    public static void main(String[] args) {
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("currencies.txt");
        List<Currency> currencies = currencyLoaderFromFile.currencyLoader();
        ExchangeRateLoaderFromWS exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWS();
        DialogSwing dialogSwing = new DialogSwing(currencies);
        
        new MCController(dialogSwing, 
                         exchangeRateLoaderFromWebService);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoneyCalculatorGUI(dialogSwing, "Money Calculator");        
            }
        });
    }
}