package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Endereco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GerarArquivo {
    public static void salvarListaJson(List<Endereco> enderecos) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()//formata o json
                .create();

        try (FileWriter escrita = new FileWriter("enderecos.json")) {
            escrita.write(gson.toJson(enderecos));
        } catch (IOException ex) {
            System.out.println("Erro na gravação");
        }
    }

    public static void salvarEnderecoJson(Endereco endereco) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()//formata o json
                .create();

        try (FileWriter escrita = new FileWriter(endereco.cep() + ".json")) {
            escrita.write(gson.toJson(endereco));
        } catch (IOException ex) {
            System.out.println("Erro na gravação");
        }
    }
}
