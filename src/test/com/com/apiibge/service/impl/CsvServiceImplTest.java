package com.apiibge.service.impl;

import com.apiibge.convert.ConvertToCSV;
import com.apiibge.request.IbgeVO;
import com.apiibge.service.IbgeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


public class CsvServiceImplTest {

    @Mock
    private IbgeService ibgeService;

    @Mock
    private ConvertToCSV convertToCSV;

    @InjectMocks
    private CsvServiceImpl csvService;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_Call_In_Services(){
        // Mock
        List<IbgeVO> ibgeVOS = Arrays.asList(new IbgeVO());

        Mockito.when(ibgeService.getDataOfStates())
                .thenReturn(ibgeVOS);

        // Method call
        csvService.getAllCitiesFormatted(response);

        // Verify
        Mockito.verify(ibgeService).getDataOfStates();
        Mockito.verify(convertToCSV).convert(ibgeVOS, response);
    }


}