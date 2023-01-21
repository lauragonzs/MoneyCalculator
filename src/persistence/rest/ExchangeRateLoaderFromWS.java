package persistence.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import model.Currency;
import model.ExchangeRate;
import persistence.ExchangeRateLoader;

public class ExchangeRateLoaderFromWS implements ExchangeRateLoader {

    @Override
    public ExchangeRate exchangeRateLoader(Currency currencyFrom, Currency currencyTo) {
        try {
            return new ExchangeRate(currencyFrom, currencyTo, read(currencyFrom.getCode(), currencyTo.getCode()));
        } catch (MalformedURLException exception) {
            System.out.println("ExchangeRateLoaderFromWebService : exchangerateLoader, MalformedURLException" + exception.getMessage());
            return null;
        } catch (IOException exception) {
            System.out.println("ExchangeRateLoaderFromWebService : exchangerateLoader, IOException" + exception.getMessage());
            return null;
        }
    }

    private Double read(String codeFrom, String codeTo) throws MalformedURLException, IOException {
        String line = URLread(new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + codeFrom + "/" + codeTo + ".json"));
        return Double.parseDouble(getRateFromJSON(line));
    }

    private String URLread(URL url) throws IOException {
        InputStream inputStream = url.openStream();     
        byte[] buffer = new byte[1024];
        return new String(buffer, 0, inputStream.read(buffer));
    }
    
    private String getRateFromJSON(String line) {
        String[] split = line.split(",");
        return split[1].substring(split[1].indexOf(":") + 1, split[1].indexOf("}") - 1);
    }
}
