package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo de dados (relativo ao diretório de recursos)
        String caminhoArquivo = "dados_produto.txt";

        // Ler os dados do arquivo
        Map<String, String> dados = LeitorArquivo.lerDados(caminhoArquivo);

        // Verificar se os dados foram carregados corretamente
        if (dados.isEmpty()) {
            System.out.println("Não foi possível carregar os dados do arquivo.");
            return;
        }

        // Criar o produto usando o ProdutoService
        ProdutoService produtoService = new ProdutoService();
        try {
            Produto produto = produtoService.criarProduto(dados);

            // Exibir o produto criado
            System.out.println("Produto cadastrado com sucesso:");
            System.out.println(produto);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar o produto: " + e.getMessage());
        }
    }
}