package pl.javastart.task.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EuroStatistics {
    private final List<Product> euroProducts = new ArrayList<>();
    private Product cheapestProduct;
    private Product mostExpensiveProduct;
    private BigDecimal totalProductsValue = new BigDecimal(0);

    public EuroStatistics(List<Product> products, List<Currency> currencies) {
        Product origProduct;
        Product convertedPriceProduct;
        BigDecimal origPrice;
        BigDecimal euroValue;
        BigDecimal exchangeRate;

        for (Product product : products) {
            origProduct = product;
            origPrice = origProduct.getPrice();
            exchangeRate = getCurrencyExchangeRate(currencies, origProduct.getCurrency());

            /*
            W poniższej linii nie byłem pewien, jaka dokładnie precyzja wyświetlanych wyników jest oczekiwana,
            wybrałem więc jako skalę najpierw 10, a potem podwyższyłem ją do 20.
             */
            euroValue = origPrice.divide(exchangeRate, 20, RoundingMode.UP).stripTrailingZeros();
            convertedPriceProduct = new Product(origProduct, euroValue);
            euroProducts.add(convertedPriceProduct);
            updateStatistics(convertedPriceProduct);
        }
    }

    private void updateStatistics(Product convertedPriceProduct) {
        BigDecimal price = convertedPriceProduct.getPrice();

        totalProductsValue = totalProductsValue.add(price);

        if (cheapestProduct == null) {
            cheapestProduct = convertedPriceProduct;
            mostExpensiveProduct = convertedPriceProduct;
            return;
        }

        if (price.compareTo(cheapestProduct.getPrice()) < 0) {
            cheapestProduct = convertedPriceProduct;
        } else if (price.compareTo(mostExpensiveProduct.getPrice()) > 0) {
            mostExpensiveProduct = convertedPriceProduct;
        }
    }

    private BigDecimal getCurrencyExchangeRate(List<Currency> currencies, String origCurrency) {
        if (origCurrency.equals("EUR")) {
            return new BigDecimal(1);
        }

        for (Currency currency : currencies) {
            if (currency.currencyName().equals(origCurrency)) {
                return currency.exchangeRate();
            }
        }

        throw new NoSuchElementException("Nie znaleziono kursu podanej waluty " + origCurrency + " w pliku wejściowym.");
    }

    public Product getCheapestProduct() {
        /*
        Poniżej zapewniam (tak mi się przynajmniej wydaje) niemutowalność zwracanego obiektu, dzięki czemu
        z zewnątrz osoba korzystająca z tej metody nie będzie mieć wpływu na stan obiektu EuroStatistics. Każde z pól
        klasy Product również jest typu niemutowalnego
         */
        return new Product(cheapestProduct.getName(), cheapestProduct.getPrice(), cheapestProduct.getCurrency());
    }

    public Product getMostExpensiveProduct() {
        return new Product(mostExpensiveProduct.getName(), mostExpensiveProduct.getPrice(), mostExpensiveProduct.getCurrency());
    }

    public BigDecimal getTotalProductsValue() {
        return totalProductsValue;
    }

    public BigDecimal getProductsAvgValue() {
        return totalProductsValue.divide(new BigDecimal(euroProducts.size()), 20, RoundingMode.UP).stripTrailingZeros();
    }

}
