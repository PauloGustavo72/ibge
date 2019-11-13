package com.apiibge.client.impl;

import com.apiibge.client.IbgeClient;
import com.apiibge.exception.CitiesNotFoundException;
import com.apiibge.exception.RegionNotFoundException;
import com.apiibge.request.City;
import com.apiibge.request.Region;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IbgeClientImpl implements IbgeClient {
    private static final Logger logger = LogManager.getLogger(IbgeClientImpl.class);

    private RestTemplate restTemplate;

    private String baseUrl;

    private ObjectMapper mapper;

    public IbgeClientImpl(@Value("${ibge.url}") String baseUrl, ObjectMapper mapper) {
        this.baseUrl = baseUrl;
        this.mapper = mapper;
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build()));
    }

    @Override
    public List<Region> getAllRegions(){
        String body = restTemplate.getForEntity(baseUrl, String.class).getBody();

        try {
            return mapper.readValue(body,
                    mapper.getTypeFactory().constructCollectionType(List.class, Region.class));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RegionNotFoundException("Regions notFound");
        }
    }

    @Override
    public List<City> getCitiesByStateId(Long stateId){
        String body = restTemplate.getForEntity(baseUrl + "{stateId}/municipios" , String.class, stateId)
                .getBody();
        try {
            return mapper.readValue(body,
                    mapper.getTypeFactory().constructCollectionType(List.class, City.class));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new CitiesNotFoundException( String.format( "Cities with regionId = %s notFound", stateId.toString() ));
        }
    }

}
