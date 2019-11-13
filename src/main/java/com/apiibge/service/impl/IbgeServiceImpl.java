package com.apiibge.service.impl;

import com.apiibge.client.IbgeClient;
import com.apiibge.convert.ConvertCityInVO;
import com.apiibge.repository.RedisRepository;
import com.apiibge.request.City;
import com.apiibge.request.IbgeVO;
import com.apiibge.request.Region;
import com.apiibge.service.IbgeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IbgeServiceImpl implements IbgeService {
    private static final Logger logger = LogManager.getLogger(IbgeServiceImpl.class);

    private IbgeClient ibgeClient;
    private ConvertCityInVO convertCityInVO;
    private RedisRepository redisRepository;

    @Autowired
    public IbgeServiceImpl(IbgeClient ibgeClient, ConvertCityInVO convertCityInVO, RedisRepository redisRepository) {
        this.ibgeClient = ibgeClient;
        this.convertCityInVO = convertCityInVO;
        this.redisRepository = redisRepository;
    }

    @Override
    public List<IbgeVO> getDataOfStates() {
        logger.info("Searching IbgeVOS in cache ...");
        List<IbgeVO> ibgeVOS = redisRepository.getAllIbgeVOS();

        if(ibgeVOS == null || ibgeVOS.isEmpty()) {
            logger.info("Has not IbgeVOS in cache ...");
            List<City> cities = getCities();

            logger.info("Starting IbgeVOS conversion ...");
            ibgeVOS = convertCityInVO.convert(cities);
            logger.info("Finished IbgeVOS conversion ...");

            redisRepository.saveAllIbgeVOS(ibgeVOS);
        }
        logger.info("Returning IbgeVOS ...");
        return ibgeVOS;
    }

    @Override
    public List<City> getCities() {
        logger.info("Searching cities in cache ...");
        List<City> cities = redisRepository.getAllCities();

        if(cities == null || cities.isEmpty()) {
            logger.info("No cities in cache ...");
            List<Region> regions = redisRepository.getAllIRegions();

            logger.info("Searching regions in cache ...");
            if(regions == null || regions.isEmpty()) {
                logger.info("No regions in cache ...");
                regions = ibgeClient.getAllRegions();
                redisRepository.saveAllRegions(regions);
            }

            logger.info("Now has Regions in cache ...");

            cities = new ArrayList<>();

            for (Region region : regions) {
                logger.info("Searching cities where region_id = ", region.getId());
                List<City> citysByStateId = ibgeClient.getCitiesByStateId(region.getId());
                logger.info("Search completed, found " + citysByStateId.size() + " cities ..." );
                cities.addAll(citysByStateId);
            }

            redisRepository.saveAllCities(cities);
        }
        logger.info("Has cities in cache ...");
        return cities;
    }

}
