package com.apiibge.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class City implements Serializable {

    private Long id;
    private String nome;
    private Microrregiao microrregiao;

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

    public Microrregiao getMicrorregiao() {
        return microrregiao;
    }

    public void setMicrorregiao(Microrregiao microrregiao) {
        this.microrregiao = microrregiao;
    }

    @JsonIgnore
    public Long getIdEstado() {
        return microrregiao.getMesorregiao().getUf().getId();
    }

    @JsonIgnore
    public String getSiglaEstado() {
        return microrregiao.getMesorregiao().getUf().getSigla();
    }

    @JsonIgnore
    public String getRegiaoNome() {
        return microrregiao.getMesorregiao().getUf().getRegiao().getNome();
    }

    @JsonIgnore
    public String getNomeMesorregiao() {
        return microrregiao.getMesorregiao().getNome();
    }

    @JsonIgnore
    public String getNomeFormatado() {
        return this.nome + "/" + getSiglaEstado();
    }
}
