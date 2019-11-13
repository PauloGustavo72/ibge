package com.apiibge.convert.impl;

import com.apiibge.builder.IbgeVOBuilder;
import com.apiibge.convert.ConvertCityInVO;
import com.apiibge.request.City;
import com.apiibge.request.IbgeVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertCityInVOImpl implements ConvertCityInVO {

    @Override
    public List<IbgeVO> convert(List<City> cities) {
        List<IbgeVO> ibgeVOS = new ArrayList<>();

        for (City city : cities) {
            ibgeVOS.add( convertCityToIbgeVO(city) );
        }
        return ibgeVOS;
    }

    private IbgeVO convertCityToIbgeVO(City city) {

        return new IbgeVOBuilder()
                    .withIdEstado(city.getIdEstado())
                    .withSiglaEstado(city.getSiglaEstado())
                    .withRegiaoNome(city.getRegiaoNome())
                    .withNomeCidade(city.getNome())
                    .withNomeMesorregiao(city.getNomeMesorregiao())
                    .withNomeFormatado(city.getNomeFormatado())
                    .build();
    }


}
