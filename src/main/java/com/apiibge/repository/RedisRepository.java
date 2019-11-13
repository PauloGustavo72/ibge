package com.apiibge.repository;

import com.apiibge.request.City;
import com.apiibge.request.IbgeVO;
import com.apiibge.request.Region;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisRepository {

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    private final static String KEY_IBGE = "IBGE";
    private final static String KEY_REGION = "REGION";
    private final static String KEY_CITIES = "CITIES";
    private final static String KEY_NAME_CITY = "NAME-CITY";

    public RedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void saveIdOfCity(String nameOfCity, Long id){
        hashOperations.put(KEY_NAME_CITY, nameOfCity, id);
    }

    public Long getIdOfCityByName(String nameOfCity){
        return (Long) hashOperations.get(KEY_NAME_CITY, nameOfCity);
    }

    public List<IbgeVO> getAllIbgeVOS(){
        return (List<IbgeVO>) redisTemplate.opsForValue().get(KEY_IBGE);
    }

    public void saveAllIbgeVOS(List<IbgeVO> ibgeVOS){
        redisTemplate.opsForValue().set(KEY_IBGE, ibgeVOS);
    }

    public List<Region> getAllIRegions(){
        return (List<Region>) redisTemplate.opsForValue().get(KEY_IBGE);
    }

    public void saveAllRegions(List<Region> regions){
        redisTemplate.opsForValue().set(KEY_REGION, regions);
    }

    public List<City> getAllCities(){
        return (List<City>) redisTemplate.opsForValue().get(KEY_CITIES);
    }

    public void saveAllCities(List<City> cities){
        redisTemplate.opsForValue().set(KEY_CITIES, cities);
    }

}
