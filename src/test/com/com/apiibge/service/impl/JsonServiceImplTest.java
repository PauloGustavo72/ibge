package com.apiibge.service.impl;

import com.apiibge.request.IbgeVO;
import com.apiibge.service.IbgeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class JsonServiceImplTest {

    @Mock
    private IbgeService ibgeService;

    @InjectMocks
    private JsonServiceImpl jsonService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_Call_Serices(){
        // Mock
        Mockito.when(ibgeService.getDataOfStates())
                .thenReturn(Arrays.asList(new IbgeVO()));

        // Method call
        jsonService.getAllCitiesFormatted();

        // Verify
        Mockito.verify(ibgeService).getDataOfStates();
    }

}