package com.apiibge.controller;

import com.apiibge.request.IbgeVO;
import com.apiibge.service.JsonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonController {
    private static final Logger logger = LogManager.getLogger(CsvController.class);

    @Autowired
    private JsonService jsonService;

    @GetMapping(value = "/v1/ibge/json", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<IbgeVO>> getAllStates(){
        logger.info("Start of Json Route ... ");
        List<IbgeVO> ibgeVOS = jsonService.getAllCitiesFormatted();
        logger.info("End of the Json Route ...");
        return new ResponseEntity<>(ibgeVOS, HttpStatus.OK);
    }
}
