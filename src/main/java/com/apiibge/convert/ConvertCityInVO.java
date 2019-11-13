package com.apiibge.convert;

import com.apiibge.request.City;
import com.apiibge.request.IbgeVO;

import java.util.List;

public interface ConvertCityInVO {

    List<IbgeVO> convert(List<City> cities);
}
