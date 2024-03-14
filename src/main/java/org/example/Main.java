package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Customer cliente1 = new Customer(1L,"primo cliente", 1);
        Customer cliente2 = new Customer(2L,"primo cliente", 2);
        Customer cliente3 = new Customer(3L,"primo cliente", 3);

        List<Product> products = new ArrayList<>();
        products.add(new Product(1L,"libro","Books",29.99));
        products.add(new Product(2L,"dizionario","Books",49.90));
        products.add(new Product(3L,"laptop","Pc",190.89));


        List <Order> ordini = new ArrayList<>();
        ordini.add(new Order(1L,"delivered", LocalDate.of(2019,2,11),LocalDate.of(2019,2,15),List.of(products.get(0)),cliente1));
        ordini.add(new Order(2L,"shipped", LocalDate.of(2018,4,18),LocalDate.of(2018,4,23),List.of(products.get(1)),cliente2));
        ordini.add(new Order(3L,"processed", LocalDate.of(2021,7,17),LocalDate.of(2022,7,15),List.of(products.get(2)),cliente3));

        Map <Customer, List <Order>> ordinipercliente = ordini.stream().collect(Collectors.groupingBy(Order::getCustomer));
       ordinipercliente.forEach((cliente,ordinicliente) -> {
            System.out.println("Cliente: " + cliente.getName());
            System.out.println("Ordini:");
            ordinicliente.forEach(order -> {
                System.out.println("ID: " + order.getId() + ", Stato: " + order.getStatus());
            });
            System.out.println();

            // Esercizio2
            Map <Customer, Double> venditecliente = ordini.stream().collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())));
            venditecliente.forEach((clienti,venditetotali) -> {
                System.out.println("Cliente :" + clienti.getName() + ", vendite totali: " + venditetotali );

            });
        });


            // Esercizio 3

        Optional<Product> prodottopiucostoso = products.stream().max(Comparator.comparing(Product::getPrice));
        prodottopiucostoso.ifPresent(product -> {
            System.out.println();
            System.out.println("Prodotto più costoso ");
            System.out.println("nome del prodotto: "+ product.getName());
            System.out.println("categoria del prodotto: " + product.getCategory());
            System.out.println("prezzo prodotto: " + product.getPrice());
        });




    }

    }

