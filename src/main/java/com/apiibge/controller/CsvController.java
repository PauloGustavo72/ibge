package com.apiibge.controller;

import com.apiibge.service.CsvService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CsvController {
    private static final Logger logger = LogManager.getLogger(CsvController.class);

    @Autowired
    private CsvService csvService;

    @GetMapping(value = "/v1/ibge/csv")
    public ResponseEntity getStatesWithReturnOfCSV(HttpServletResponse response) throws IOException {
        logger.info("Start of csv Route ... ");
        csvService.getAllCitiesFormatted(response);
        logger.info("End of the csv Route ...");
        return new ResponseEntity( HttpStatus.OK);
    }
}
