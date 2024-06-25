package model;

public record Endereco(
        String cep,
        String siafi,
        String ibge,
        String bairro,
        String localidade,
        String uf,
        String ddd) {
}
