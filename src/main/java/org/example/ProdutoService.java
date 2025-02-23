package org.example;

import java.time.LocalDate;
import java.util.Map;

public class ProdutoService {
    /**
     * Cria um produto a partir de um mapa de dados.
     *
     * @param dados Mapa contendo os dados do produto.
     * @return Objeto Produto criado.
     * @throws IllegalArgumentException Se algum dado estiver faltando ou for inválido.
     */
    public Produto criarProduto(Map<String, String> dados) {
        // Extrair os valores do mapa
        long id = Long.parseLong(dados.get("id"));
        String codigoEan = dados.get("codigoEan");
        String descricao = dados.get("descricao");
        String marca = dados.get("marca");
        String modelo = dados.get("modelo");
        double preco = Double.parseDouble(dados.get("preco"));
        String categoria = dados.get("categoria");
        String urlFoto = dados.get("urlFoto");
        int estoque = Integer.parseInt(dados.get("estoque"));

        // Criar o produto com os dados do arquivo
        return new Produto.Builder(id, codigoEan, descricao)
                .marca(marca)
                .modelo(modelo)
                .preco(preco)
                .categoria(categoria)
                .urlFoto(urlFoto)
                .estoque(estoque)
                .dataUltimaAtualizacao(LocalDate.now())
                .build();
    }
}