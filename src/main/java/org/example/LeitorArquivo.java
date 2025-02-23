package org.example;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LeitorArquivo {
    public static Map<String, String> lerDados(String caminhoArquivo) {
        Map<String, String> dados = new HashMap<>();

        // Carrega o arquivo como um recurso
        try (InputStream inputStream = LeitorArquivo.class.getClassLoader().getResourceAsStream(caminhoArquivo);
             Scanner scanner = new Scanner(inputStream)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split("="); // Divide a linha em chave e valor
                if (partes.length == 2) {
                    dados.put(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }

        return dados;
    }
}