package com.apiibge.controller;

import com.apiibge.request.CityVO;
import com.apiibge.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private static final Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @GetMapping("/v1/ibge/city/{nomeCidade}")
    public ResponseEntity getIdOfCityByName(@PathVariable(value = "nomeCidade") String nameCity) throws Exception {
        logger.info("received city with name: " + nameCity);
        CityVO cityByName = cityService.getCityByName(nameCity);
        logger.info("finished id city search");
        return new ResponseEntity(cityByName, HttpStatus.OK);
    }

}
