package pl.javastart.task.model;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final String currency;

    public Product(String name, BigDecimal price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public Product(Product origProduct, BigDecimal price) {   // lekko zmodyfikowana wersja konstruktora kopiującego
        this.price = price;
        this.name = origProduct.name;
        this.currency = "EUR";
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
