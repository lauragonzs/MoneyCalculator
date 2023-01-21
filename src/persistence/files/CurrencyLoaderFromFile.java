package persistence.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Currency;
import persistence.CurrencyLoader;

public class CurrencyLoaderFromFile implements CurrencyLoader {
    private final String fileName;
    
    public CurrencyLoaderFromFile(String fileName) {
        this.fileName = fileName;
    }    

    @Override
    public List<Currency> currencyLoader() {
        List<Currency> currenciesList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.fileName)));
            IteratorReader iteratorReader = new IteratorReader(reader);
            for (String line: iteratorReader) {
                String[] split = line.split(",");
                currenciesList.add(new Currency(split[0], split[1], split[2]));
            }
            Collections.sort(currenciesList, (x, y) -> x.getName().compareToIgnoreCase(y.getName()));

        } catch (FileNotFoundException exception) {
            System.out.println("CurrencyLoaderFromFile: currencyLoader, FileNotFoundException " + exception.getMessage());
        }
        return currenciesList;
    }

}
