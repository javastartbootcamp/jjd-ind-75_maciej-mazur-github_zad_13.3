package pl.javastart.task.model;

import java.math.BigDecimal;

public record Currency(String currencyName, BigDecimal exchangeRate) {
}
