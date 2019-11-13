package com.apiibge.service.impl;

import com.apiibge.request.IbgeVO;
import com.apiibge.service.IbgeService;
import com.apiibge.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonServiceImpl implements JsonService {

    @Autowired
    private IbgeService ibgeService;

    public List<IbgeVO> getAllCitiesFormatted() {
        List<IbgeVO> citiesFormatted = ibgeService.getDataOfStates();

        return citiesFormatted;
    }
}
