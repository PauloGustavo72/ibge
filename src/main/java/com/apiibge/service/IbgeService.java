package com.apiibge.service;

import com.apiibge.request.City;
import com.apiibge.request.IbgeVO;

import java.util.List;

public interface IbgeService {
    List<IbgeVO> getDataOfStates();

    List<City> getCities();
}
