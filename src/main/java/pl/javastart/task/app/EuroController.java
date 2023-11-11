package pl.javastart.task.app;

import static pl.javastart.task.io.ConsolePrinter.*;

import pl.javastart.task.io.file.FileManager;
import pl.javastart.task.model.Currency;
import pl.javastart.task.model.EuroStatistics;
import pl.javastart.task.model.Product;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

public class EuroController {
    public void run() {
        FileManager fileManager = new FileManager();

        try {
            List<Product> products = fileManager.importProducts();
            List<Currency> currencies = fileManager.importCurrencies();
            System.out.println();
            EuroStatistics euroStatistics = new EuroStatistics(products, currencies);
            //printAll(euroStatistics);
            printEuroSum(euroStatistics);
            printEuroAvg(euroStatistics);
            printMostExpensiveProduct(euroStatistics);
            printCheapestProduct(euroStatistics);
        } catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            printLine(e.getMessage());
        }
    }
}
