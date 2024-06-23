package br.dev.hygino.model;

public record Product(String name, Double price) implements Comparable<Product> {
    @Override
    public int compareTo(Product otherProduct) {
        return this.price().compareTo(otherProduct.price());
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
