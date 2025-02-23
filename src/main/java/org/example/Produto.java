package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class Produto {
    private final long id;
    private final String codigoEan;
    private final String descricao;
    private final String marca;
    private final String modelo;
    private final double preco;
    private final LocalDate dataCadastro;
    private LocalDate dataUltimaAtualizacao;
    private int estoque;
    private final String categoria;
    private final String urlFoto;

    private Produto(Builder builder) {
        this.id = builder.id;
        this.codigoEan = builder.codigoEan;
        this.descricao = builder.descricao;
        this.marca = builder.marca;
        this.modelo = builder.modelo;
        this.preco = builder.preco;
        this.dataCadastro = builder.dataCadastro;
        this.dataUltimaAtualizacao = builder.dataUltimaAtualizacao;
        this.estoque = builder.estoque;
        this.categoria = builder.categoria;
        this.urlFoto = builder.urlFoto;
    }

    // Getters (sem setters para garantir imutabilidade)
    public long getId() {
        return id;
    }

    public String getCodigoEan() {
        return codigoEan;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPreco() {
        return preco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public LocalDate getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    // Método para atualizar estoque
    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
        this.estoque = estoque;
        this.dataUltimaAtualizacao = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", codigoEan='" + codigoEan + '\'' +
                ", descricao='" + descricao + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", preco=" + preco +
                ", dataCadastro=" + dataCadastro +
                ", dataUltimaAtualizacao=" + dataUltimaAtualizacao +
                ", estoque=" + estoque +
                ", categoria='" + categoria + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }

    // Builder
    public static class Builder {
        private final long id;
        private final String codigoEan;
        private final String descricao;
        private String marca;
        private String modelo;
        private double preco;
        private final LocalDate dataCadastro = LocalDate.now();
        private LocalDate dataUltimaAtualizacao;
        private int estoque = 0;
        private String categoria;
        private String urlFoto;

        // Atributos obrigatórios
        public Builder(long id, String codigoEan, String descricao) {
            this.id = id;
            this.codigoEan = Objects.requireNonNull(codigoEan, "Código EAN não pode ser nulo.");
            this.descricao = Objects.requireNonNull(descricao, "Descrição não pode ser nula.");
        }

        public Builder marca(String marca) {
            this.marca = Objects.requireNonNull(marca, "Marca não pode ser nula.");
            return this;
        }

        public Builder modelo(String modelo) {
            if (this.marca == null) {
                throw new IllegalStateException("A marca deve ser informada antes do modelo.");
            }
            this.modelo = Objects.requireNonNull(modelo, "Modelo não pode ser nulo.");
            return this;
        }

        public Builder preco(double preco) {
            if (preco <= 0) {
                throw new IllegalArgumentException("Preço deve ser maior que zero.");
            }
            this.preco = preco;
            return this;
        }

        public Builder categoria(String categoria) {
            this.categoria = Objects.requireNonNull(categoria, "Categoria não pode ser nula.");
            return this;
        }

        public Builder urlFoto(String urlFoto) {
            this.urlFoto = Objects.requireNonNull(urlFoto, "URL da foto não pode ser nula.");
            return this;
        }

        public Builder dataUltimaAtualizacao(LocalDate dataUltimaAtualizacao) {
            if (dataUltimaAtualizacao.isBefore(this.dataCadastro)) {
                throw new IllegalArgumentException("Data de última atualização não pode ser anterior à data de cadastro.");
            }
            this.dataUltimaAtualizacao = dataUltimaAtualizacao;
            return this;
        }

        public Builder estoque(int estoque) {
            if (estoque < 0) {
                throw new IllegalArgumentException("Estoque não pode ser negativo.");
            }
            this.estoque = estoque;
            return this;
        }

        public Produto build() {
            // Validações finais
            if (preco <= 0) {
                throw new IllegalStateException("Preço deve ser maior que zero.");
            }
            if (marca == null && modelo != null) {
                throw new IllegalStateException("A marca deve ser informada antes do modelo.");
            }
            return new Produto(this);
        }
    }
}