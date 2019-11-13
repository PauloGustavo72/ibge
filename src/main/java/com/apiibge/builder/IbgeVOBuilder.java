package com.apiibge.builder;

import com.apiibge.request.IbgeVO;

public class IbgeVOBuilder {

    private IbgeVO ibgeVO;

    public IbgeVOBuilder() {
        this.ibgeVO = new IbgeVO();
    }

    public IbgeVOBuilder withIdEstado(Long idEstado){
        this.ibgeVO.setIdEstado(idEstado);
        return this;
    }

    public IbgeVOBuilder withSiglaEstado(String siglaEstado){
        this.ibgeVO.setSiglaEstado(siglaEstado);
        return this;
    }

    public IbgeVOBuilder withRegiaoNome(String regiaoNome){
        this.ibgeVO.setRegiaoNome(regiaoNome);
        return this;
    }

    public IbgeVOBuilder withNomeCidade(String nomeCidade){
        this.ibgeVO.setNomeCidade(nomeCidade);
        return this;
    }

    public IbgeVOBuilder withNomeMesorregiao(String nomeMesorregiao){
        this.ibgeVO.setNomeMesorregiao(nomeMesorregiao);
        return this;
    }

    public IbgeVOBuilder withNomeFormatado(String nomeFormatado){
        this.ibgeVO.setNomeFormatado(nomeFormatado );
        return this;
    }

    public IbgeVO build(){
        return this.ibgeVO;
    }


}
