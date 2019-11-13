package apiibge.service;

import com.apiibge.builder.IbgeVOBuilder;
import com.apiibge.client.IbgeClient;
import com.apiibge.convert.ConvertCityInVO;
import com.apiibge.repository.RedisRepository;
import com.apiibge.request.*;
import com.apiibge.service.impl.IbgeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class IbgeServiceImplTest {

    private List<Region> regions;

    private List<City> cities;

    private List<IbgeVO> ibgeVOs;

    private IbgeVO ibgeVO;

    @InjectMocks private IbgeServiceImpl ibgeServiceImpl;

    @Mock private ConvertCityInVO convertCityInVO;
    @Mock private IbgeClient ibgeClient;
    @Mock private RedisRepository redisRepository;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        ibgeVO = getIbgeVO();
        setRegionsAndCities();
    }

    @Test
    public void should_Return_Formatted_IbgeVOS(){
        // Mock
        when(ibgeClient.getAllRegions()).thenReturn(regions);
        when(ibgeClient.getCitiesByStateId(regions.get(0).getId())).thenReturn(cities);
        when(convertCityInVO.convert(cities)).thenReturn(Arrays.asList(ibgeVO));

        // Method call
        ibgeVOs =  ibgeServiceImpl.getDataOfStates();

        // Assertions
        Assertions.assertThat(ibgeVOs).contains(ibgeVO);

        Mockito.verify(redisRepository).saveAllIbgeVOS(Mockito.anyList());
    }

    @Test
    public void should_Return_Formatted_IbgeVOS_When_ObjectIsCached(){
        // Mock
        ibgeVO = getIbgeVO();
        when(redisRepository.getAllIbgeVOS()).thenReturn(Arrays.asList(ibgeVO));

        // Method call
        ibgeVOs =  ibgeServiceImpl.getDataOfStates();

        // Assertions
        Assertions.assertThat(ibgeVOs).contains(ibgeVO);

        Mockito.verify(redisRepository, Mockito.never()).saveAllIbgeVOS(Mockito.anyList());
    }

    @Test
    public void should_Return_Cities_By_Cache(){
        // Mock
        when(redisRepository.getAllCities())
                .thenReturn(cities);

        // Method call
        List<City> cities = ibgeServiceImpl.getCities();

        // Assertions
        assertionsCity(cities);

        Mockito.verify(redisRepository, Mockito.never()).saveAllCities(cities);
    }

    @Test
    public void should_Return_Cities_When_City_Is_Not_In_Cache(){
        // Mock
        when(redisRepository.getAllIRegions())
                .thenReturn(regions);

        when(ibgeClient.getCitiesByStateId(Mockito.anyLong()))
                .thenReturn(cities);

        // Method call
        List<City> cities = ibgeServiceImpl.getCities();

        // Assertions
        assertionsCity(cities);
        Mockito.verify(redisRepository).saveAllCities(cities);
        Mockito.verify(ibgeClient, Mockito.never()).getAllRegions();
    }

    @Test
    public void should_Return_Cities_When_Region_Is_Not_In_Cache(){
        // Mock
        when(ibgeClient.getAllRegions())
                .thenReturn(regions);

        when(ibgeClient.getCitiesByStateId(Mockito.anyLong()))
                .thenReturn(cities);

        // Method call
        List<City> cities = ibgeServiceImpl.getCities();

        // Assertions
        assertionsCity(cities);
        Mockito.verify(redisRepository).saveAllCities(cities);
        Mockito.verify(redisRepository).saveAllRegions(regions);
        Mockito.verify(ibgeClient).getAllRegions();
    }

    private IbgeVO getIbgeVO() {
        return new IbgeVOBuilder().withIdEstado(35L)
                .withSiglaEstado("SP")
                .withRegiaoNome("Sudeste")
                .withNomeCidade("São José dos Campos")
                .withNomeMesorregiao("Vale do Paraíba Paulista")
                .withNomeFormatado("São José dos Campos/SP")
                .build();
    }

    private void assertionsCity(List<City> cities) {

        City city = cities.get(0);
        Microrregiao microrregiao = city.getMicrorregiao();
        Mesorregiao mesorregiao = microrregiao.getMesorregiao();
        UF uf = mesorregiao.getUf();
        Regiao regiao = uf.getRegiao();

        assertEquals(3549904L, city.getId().longValue());
        assertEquals("São José dos Campos", city.getNome());

        assertEquals(35050L,microrregiao.getId().longValue());
        assertEquals("São José dos Campos", microrregiao.getNome());

        assertEquals(3513L, mesorregiao.getId().longValue());
        assertEquals("Vale do Paraíba Paulista", mesorregiao.getNome());

        assertEquals(35L, uf.getId().longValue());
        assertEquals("SP", uf.getSigla());
        assertEquals("São Paulo", uf.getNome());

        assertEquals(3L, regiao.getId().longValue());
        assertEquals("SE", regiao.getSigla());
        assertEquals("Sudeste", regiao.getNome());
    }

    private void setRegionsAndCities() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        regions = mapper.readValue(new File("src/test/resources/regions.json"),
                mapper.getTypeFactory().constructCollectionType(List.class, Region.class));

        cities = mapper.readValue(new File("src/test/resources/citys.json"),
                mapper.getTypeFactory().constructCollectionType(List.class, City.class));
    }
}