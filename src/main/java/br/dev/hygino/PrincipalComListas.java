package br.dev.hygino;

import br.dev.hygino.model.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

interface Listas {
    List<String> execute(List<String> list);
}

public class PrincipalComListas {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Gorete", "Dolores", "Teresa", "Damares", "Julieta", "Melania", "Dilma");
        //nomes.stream().filter(x -> x.startsWith("D")).forEach(System.out::println);
        //final var nomesComD = buscaPorNomeIniciandoCom(nomes, "D");
        //nomesComD.forEach(System.out::println);
        //Collections.sort(nomes);
        // nomes.forEach(System.out::println);

        List<Person> pessoas = Arrays.asList(
                new Person("Gorete", "goretinha@zmail.com", "Rua das Antas 804"),
                new Person("Jupira", "ajupirinha@getmail.com", "Rua das Flores 505"),
                new Person("Dilma", "dilma@opressora.net", "Rua das Antas 804")
        );

       // pessoas.sort(Person::compareTo);
       // pessoas.forEach(System.out::println);

        Listas maiusculas = list -> list.stream().map(String::toUpperCase).toList();

        maiusculas.execute(nomes).forEach(System.out::println);

        pessoas.sort(Comparator.comparing(Person::getEmail));
        pessoas.forEach(System.out::println);
    }

    public static List<String> buscaPorNomeIniciandoCom(List<String> nomes, String nome) {
        return nomes.stream().filter(x -> x.startsWith(nome)).sorted().toList();
    }
}
