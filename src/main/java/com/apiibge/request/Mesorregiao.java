package com.apiibge.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Mesorregiao implements Serializable {

    private Long id;
    private String nome;
    private UF uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("UF")
    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
}
