package br.dev.hygino.model;

import br.dev.hygino.exceptions.OutOfBalanceException;

import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    private final Double limit;
    private Double balance;
    private final List<Product> products;

    public CreditCard(Double limit) {
        this.limit = limit;
        balance = limit;
        products = new ArrayList<>();
    }

    public Double getLimit() {
        return limit;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product.price() < balance) {
            balance -= product.price();
            products.add(product);
        } else {
            throw new OutOfBalanceException("Saldo insuficiente para essa compra!");
        }
    }

    public void showProducts() {
        products.forEach(System.out::println);
    }
}
