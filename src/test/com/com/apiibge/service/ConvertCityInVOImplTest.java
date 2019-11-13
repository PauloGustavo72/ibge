package com.apiibge.service;

import com.apiibge.convert.impl.ConvertCityInVOImpl;
import com.apiibge.request.City;
import com.apiibge.request.IbgeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ConvertCityInVOImplTest {

    private ConvertCityInVOImpl convertCityInVOImpl;

    private List<City> cities;

    @Before
    public void setUp() throws IOException {
        convertCityInVOImpl = new ConvertCityInVOImpl();
    }

    @Test
    public void should_Return_Converted_Object() throws IOException {
        // Mock
        cities = getObjectByFile();

        // Method call
        List<IbgeVO> ibgeVOS = convertCityInVOImpl.convert(cities);

        // Assertions
        IbgeVO firstCity  = ibgeVOS.get(0);

        assertNotNull(firstCity);
        assertTrue(ibgeVOS.size() == 1);
        assertEquals(35L, firstCity.getIdEstado().longValue());
        assertEquals("SP", firstCity.getSiglaEstado());
        assertEquals("Sudeste", firstCity.getRegiaoNome());
        assertEquals("São José dos Campos", firstCity.getNomeCidade());
        assertEquals("Vale do Paraíba Paulista", firstCity.getNomeMesorregiao());
        assertEquals("São José dos Campos/SP", firstCity.getNomeFormatado());

    }

    private List<City> getObjectByFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("src/test/resources/citys.json"),
                mapper.getTypeFactory().constructCollectionType(List.class, City.class));
    }

}