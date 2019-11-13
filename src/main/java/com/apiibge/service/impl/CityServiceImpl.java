package com.apiibge.service.impl;

import com.apiibge.exception.CityNoTFoundException;
import com.apiibge.repository.RedisRepository;
import com.apiibge.request.City;
import com.apiibge.request.CityVO;
import com.apiibge.service.CityService;
import com.apiibge.service.IbgeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger logger = LogManager.getLogger(CityServiceImpl.class);

    private Long idByCache;

    private IbgeService ibgeService;

    private RedisRepository redisRepository;

    @Autowired
    public CityServiceImpl(IbgeService ibgeService, RedisRepository redisRepository) {
        this.ibgeService = ibgeService;
        this.redisRepository = redisRepository;
    }

    @Override
    public CityVO getCityByName(String nameCity) throws Exception {
        logger.info("Start searching of city with name = ", nameCity);
        List<City> cities = ibgeService.getCities();

        if(hasNameInCache(nameCity)){
            logger.info("Has city in cache ...");
            return new CityVO(idByCache);
        }

        for (City city: cities  ) {
            if(city.getNome().equalsIgnoreCase(nameCity)){
                Long cityId = city.getId();
                redisRepository.saveIdOfCity(nameCity, cityId);
                logger.info("City with name " + nameCity + " has id = " + cityId);
                return new CityVO(cityId);
            }
        }
        throw new CityNoTFoundException(nameCity + " NotFound");
    }

    private boolean hasNameInCache(String nameCity) {
        Long idByCache = redisRepository.getIdOfCityByName(nameCity);

        if(idByCache == null || idByCache == 0){
            return false;
        }

        this.idByCache = idByCache;
        return true;
    }
}
