package util;

import com.google.gson.Gson;
import model.Endereco;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCep {

    public Endereco buscaEndereco(int cep) {
        URI endereco = URI.create(String.format("https://viacep.com.br/ws/%d/json/", cep));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();


        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Endereco.class);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível obter informações desse cep!");
        }
    }
}
