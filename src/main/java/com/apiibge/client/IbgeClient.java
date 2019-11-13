package com.apiibge.client;

import com.apiibge.request.City;
import com.apiibge.request.Region;

import java.util.List;

public interface IbgeClient {

    List<Region> getAllRegions();

    List<City> getCitiesByStateId(Long stateId);
}
