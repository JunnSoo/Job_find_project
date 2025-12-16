package com.project.codinviec.util.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.codinviec.dto.ProvinceDTO;
import com.project.codinviec.dto.WardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationHelper {
    private final ObjectMapper objectMapper;


    @Autowired
    @Qualifier("redisTemplateDb2")
    private RedisTemplate<String, String> redisTemplateDb02;
    private final String keyProvince = "PROVINCE";
    private final String keyWard = "WARD";


    public void addProvinceToRedis(List<ProvinceDTO> provinces){
       try {
           String json = objectMapper.writeValueAsString(provinces);
           redisTemplateDb02.opsForValue().set(keyProvince, json, Duration.ofDays(7));
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    public  List<ProvinceDTO> getProvineRedis(){
        try {
            String json = redisTemplateDb02.opsForValue().get(keyProvince);
            if(json == null){
                return new ArrayList<>();
            }
            List<ProvinceDTO> provinces = objectMapper.readValue(
                    json,
                    new TypeReference<List<ProvinceDTO>>() {}
            );
            return provinces;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addWardToRedis(List<WardDTO> ward){
        try {
            String json = objectMapper.writeValueAsString(ward);
            redisTemplateDb02.opsForValue().set(keyWard, json, Duration.ofDays(7));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<WardDTO> getWardRedis(){
        try {
            String json = redisTemplateDb02.opsForValue().get(keyWard);
            if(json == null){
                return new ArrayList<>();
            }
            List<WardDTO> wards = objectMapper.readValue(
                    json,
                    new TypeReference<List<WardDTO>>() {}
            );
            return wards;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
