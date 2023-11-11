package pl.javastart.task.io;

import pl.javastart.task.model.EuroStatistics;

public class ConsolePrinter {
    public static void printLine(String text) {
        System.out.println(text);
    }

    public static void printEuroSum(EuroStatistics euroStatistics) {
        System.out.printf("Suma wszystkich produktów: %s EUR%n", euroStatistics.getTotalProductsValue().toString());
    }

    public static void printEuroAvg(EuroStatistics euroStatistics) {
        System.out.printf("Średnia wartość produktów: %s EUR%n", euroStatistics.getProductsAvgValue().toString());
    }

    public static void printMostExpensiveProduct(EuroStatistics euroStatistics) {
        System.out.printf("Najdroższy produkt: %s -> %s EUR%n",
                euroStatistics.getMostExpensiveProduct().getName(), euroStatistics.getMostExpensiveProduct().getPrice());
    }

    public static void printCheapestProduct(EuroStatistics euroStatistics) {
        System.out.printf("Najtańszy produkt: %s -> %s EUR%n",
                euroStatistics.getCheapestProduct().getName(), euroStatistics.getCheapestProduct().getPrice());
    }

//    public static void printAll(EuroStatistics euroStatistics) {
//        for (Product euroProduct : euroStatistics.getEuroProducts()) {
//            System.out.println(euroProduct.getName() + " " + euroProduct.getPrice() + " " + euroProduct.getCurrency());
//        }
//    }
}
