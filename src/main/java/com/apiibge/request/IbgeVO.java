package com.apiibge.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class IbgeVO implements Serializable {

    private Long idEstado;

    private String siglaEstado;

    private String regiaoNome;

    private String nomeCidade;

    private String nomeMesorregiao;

    private String nomeFormatado;

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(String regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNomeMesorregiao() {
        return nomeMesorregiao;
    }

    public void setNomeMesorregiao(String nomeMesorregiao) {
        this.nomeMesorregiao = nomeMesorregiao;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    public void setNomeFormatado(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    @JsonIgnore
    public String getLineOfCSV(){
        return this.idEstado + "," + this.siglaEstado + "," + this.regiaoNome
                + "," + this.nomeCidade + "," + this.nomeMesorregiao + ","
                + this.nomeFormatado + " \n";
    }
}
