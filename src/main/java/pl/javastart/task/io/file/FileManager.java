package pl.javastart.task.io.file;

import pl.javastart.task.model.Currency;
import pl.javastart.task.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private static final String PRODUCTS_FILE_NAME = "products.csv";
    private static final String CURRENCIES_FILE_NAME = "currencies.csv";

    public List<Product> importProducts() throws FileNotFoundException {
        List<Product> products = new ArrayList<>();
        File file = new File(getClass().getClassLoader().getResource(PRODUCTS_FILE_NAME).getPath());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                products.add(createProductFromString(scanner.nextLine()));
            }
            return products;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Nie znaleziono pliku " + PRODUCTS_FILE_NAME);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Napotkano nieprawidłowy format liczbowy w pliku " + PRODUCTS_FILE_NAME);
        }
    }

    public List<Currency> importCurrencies() throws FileNotFoundException {
        List<Currency> currencies = new ArrayList<>();
        File file = new File(getClass().getClassLoader().getResource(CURRENCIES_FILE_NAME).getPath());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                currencies.add(createCurrencyFromString(scanner.nextLine()));
            }
            return currencies;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Nie znaleziono pliku " + CURRENCIES_FILE_NAME);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Napotkano nieprawidłowy format liczbowy w pliku " + CURRENCIES_FILE_NAME);
        }
    }

    private Currency createCurrencyFromString(String line) {
        String[] split = line.split(";");
        String currency = split[0];
        BigDecimal exchangeRate = new BigDecimal(split[1]);

        return new Currency(currency, exchangeRate);
    }

    private Product createProductFromString(String line) {
        String[] split = line.split(";");
        String name = split[0];
        BigDecimal price = new BigDecimal(split[1]);
        String currency = split[2];

        return new Product(name, price, currency);
    }
}
