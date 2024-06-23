package br.dev.hygino;

import br.dev.hygino.exceptions.OutOfBalanceException;
import br.dev.hygino.model.CreditCard;
import br.dev.hygino.model.Product;

import java.util.Scanner;

public class Desafio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // List<Product> lista = new ArrayList<>();
        var continua = true;
        CreditCard creditCard = null;
        try {
            System.out.print("Digite o limite do cartão: ");
            double saldo = sc.nextDouble();
            creditCard = new CreditCard(saldo);
        } catch (NumberFormatException nex) {
            System.out.println("Valor inválido!");
        }
        while (continua) {
            System.out.print("Digite a descrição da compra: ");
            sc.nextLine();
            String desc = sc.nextLine();
            System.out.print("Digite o preço da compra: ");
            double valor = sc.nextDouble();
            var product = new Product(desc, valor);
            System.out.println(product);

            try {
                assert creditCard != null;
                creditCard.addProduct(product);
            } catch (OutOfBalanceException e) {
                System.out.println(e.getMessage());
            }
            sc.nextLine();
            System.out.println("Continuar S/N: ");
            String opcao = sc.nextLine();
            continua = opcao.equalsIgnoreCase("s");
            //sc.nextLine();
        }
        System.out.println("Compras finalizadas");
        assert creditCard != null;
        if (!creditCard.getProducts().isEmpty()) {
            System.out.println("Itens comprados");
            creditCard.showProducts();
        }
        System.out.printf("Saldo restante %.2f\n", creditCard.getBalance());
        sc.close();
    }
}
