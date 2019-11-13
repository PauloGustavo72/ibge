package com.apiibge.service.impl;

import com.apiibge.exception.CityNoTFoundException;
import com.apiibge.repository.RedisRepository;
import com.apiibge.request.City;
import com.apiibge.request.CityVO;
import com.apiibge.service.IbgeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CityServiceImplTest {

    @InjectMocks private CityServiceImpl cityService;

    @Mock private IbgeService ibgeService;
    @Mock private RedisRepository redisRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_Return_Id_When_HasCity_With_Searched_Name() throws Exception {
        // Mock
        String nameOfCity = "São José dos Campos";
        List<City> cities = getCities();
        Mockito.when(ibgeService.getCities()).thenReturn(cities);

        //Method call
        CityVO cityVO = cityService.getCityByName(nameOfCity);

        // Assertion
        assertNotNull(cityVO);
        assertEquals(3549904, cityVO.getId().longValue());
        Mockito.verify(redisRepository).saveIdOfCity(nameOfCity, 3549904L);

    }

    @Test
    public void should_Return_Id_When_HasCity_With_Searched_Name_InCache() throws Exception {
        // Mock
        String nameOfCity = "São José dos Campos";
        List<City> cities = getCities();

        Mockito.when(ibgeService.getCities()).thenReturn(cities);
        Mockito.when(redisRepository.getIdOfCityByName(nameOfCity))
                .thenReturn(3549904L);


        //Method call
        CityVO cityVO = cityService.getCityByName(nameOfCity);

        // Assertion
        assertNotNull(cityVO);
        assertEquals(3549904L, cityVO.getId().longValue());
        Mockito.verify(redisRepository, Mockito.never()).saveIdOfCity(nameOfCity, 3549904L);
    }

    @Test(expected = CityNoTFoundException.class)
    public void test() throws Exception {
        // Mock
        List<City> cities = getCities();
        Mockito.when(ibgeService.getCities()).thenReturn(cities);

        //Method call
        cityService.getCityByName("Sãaaaaao José dos Campos");
    }

    private List<City> getCities() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
         return mapper.readValue(new File("src/test/resources/citys.json"),
                mapper.getTypeFactory().constructCollectionType(List.class, City.class));
    }
}