package com.apiibge.controller;

import com.apiibge.request.CityVO;
import com.apiibge.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Test
    public void test_CityController() throws Exception {
        when(cityService.getCityByName(Mockito.anyString()))
                .thenReturn(new CityVO(15L));

        this.mockMvc.perform(get("/v1/ibge/city/São Paulo")
                            .contentType(MediaType.APPLICATION_JSON))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.id").value(15L));

    }
}