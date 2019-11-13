package com.apiibge.service.impl;

import com.apiibge.convert.ConvertToCSV;
import com.apiibge.request.IbgeVO;
import com.apiibge.service.CsvService;
import com.apiibge.service.IbgeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService {

    private IbgeService ibgeService;

    private ConvertToCSV convertToCSV;

    public CsvServiceImpl(IbgeService ibgeService, ConvertToCSV convertToCSV) {
        this.ibgeService = ibgeService;
        this.convertToCSV = convertToCSV;
    }

    @Override
    public HttpServletResponse getAllCitiesFormatted(HttpServletResponse response) {
        List<IbgeVO> ibgeVOS = ibgeService.getDataOfStates();
         return convertToCSV.convert(ibgeVOS, response);
    }
}
