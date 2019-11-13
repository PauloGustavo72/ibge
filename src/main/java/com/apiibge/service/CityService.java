package com.apiibge.service;

import com.apiibge.request.CityVO;

public interface CityService {

    CityVO getCityByName(String nameCity) throws Exception;
}
