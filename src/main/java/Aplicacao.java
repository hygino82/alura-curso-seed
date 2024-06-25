import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Endereco;
import util.ConsultaCep;
import util.GerarArquivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaCep consulta = new ConsultaCep();
        List<Endereco> enderecos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()//formata o json
                .create();
        while (true) {
            System.out.print("Informe o cep da cidade (0 - sair)-> ");
            int cep = leitura.nextInt();
            if (cep <= 0) {
                break;
            }
            try {
                var endereco = consulta.buscaEndereco(cep);
                enderecos.add(endereco);
                System.out.println(endereco);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando a aplicação!");
                break;
            }
        }
        leitura.close();

        System.out.println("O programa finalizou corretamente!");

        if (!enderecos.isEmpty()) {
            GerarArquivo.salvarListaJson(enderecos);
            enderecos.forEach(GerarArquivo::salvarEnderecoJson);
        } else {
            System.out.println("Lista vazia");
        }
    }
}
