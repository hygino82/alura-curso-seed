package br.dev.hygino.principal;

import br.dev.hygino.exceptions.ErroDeConversaoException;
import br.dev.hygino.modelos.Titulo;
import br.dev.hygino.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()//formata o json
                .create();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.print("Informe o mome do filme -> ");
            busca = leitura.nextLine();

            String endereco = String.format("http://www.omdbapi.com/?apikey=ac2e2bd6&t=%s", busca.replace(" ", "+"));
            try {
                if (busca.equalsIgnoreCase("sair")) {
                    break;
                }
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());


                TituloOmdb meuTituloOmdb = gson.fromJson(response.body(), TituloOmdb.class);
                System.out.println(meuTituloOmdb);


                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);
            } catch (NumberFormatException ex) {
                System.out.println("Formato inválido");
                System.out.println(ex.getMessage());
            } catch (IllegalArgumentException ex2) {
                System.out.println("Texto inválido");
                System.out.println(ex2.getMessage());
            } catch (ErroDeConversaoException ex3) {
                System.out.println(ex3.getMessage());
            }
        }

        System.out.println("O programa finalizou corretamente!");

        try (FileWriter escrita = new FileWriter("filmes.json")) {
            escrita.write(gson.toJson(titulos));

        } catch (IOException ex) {
            System.out.println("Erro na gravação");
        }
        //titulos.forEach(System.out::println);
        leitura.close();
    }
}
