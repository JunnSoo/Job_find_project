package com.project.it_job.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.it_job.block.UserBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BlockUserHelpper {

    @Qualifier("redisTemplateDb0")
    private final RedisTemplate<String, String> redisTemplateDb00;

    public void updateCountErrorUser(String email){
        //                    dùng Object mapper để convert sang String
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//                        Tìm key chính là email
            if (redisTemplateDb00.hasKey(email)) {
//                            Nếu tồn tại
                String json = redisTemplateDb00.opsForValue().get(email);
                UserBlock userBlock = objectMapper.readValue(json, UserBlock.class);

                if (!userBlock.isBlocked()){
                    if (userBlock.getCountLoginError() == 15) {
//                    Block 10 phút khi sai 15 lần
                        LocalDateTime expireAt = LocalDateTime.now().plusMinutes(10);
                        userBlock.setCountLoginError(userBlock.getCountLoginError() + 1);
                        userBlock.setBlocked(true);
                        userBlock.setExpireTime(expireAt.toString());

                        String jsonUpdate = objectMapper.writeValueAsString(userBlock);
                        redisTemplateDb00.opsForValue().set(email, jsonUpdate, Duration.ofMinutes(10));
                    } else{
//                    Dưới 15 lần
                        userBlock.setCountLoginError(userBlock.getCountLoginError() + 1);
                        String jsonUpdate = objectMapper.writeValueAsString(userBlock);
                        redisTemplateDb00.opsForValue().set(email,jsonUpdate,Duration.ofDays(7));
                    }
                }
            } else {
//                Không tìm thấy key
                UserBlock userBlock = UserBlock.builder()
                        .email(email)
                        .createdAt(LocalDateTime.now().toString())
                        .countLoginError(1)
                        .expireTime(null)
                        .isBlocked(false)
                        .build();
                String json = objectMapper.writeValueAsString(userBlock);
                redisTemplateDb00.opsForValue().set(email,json,Duration.ofDays(7));
            }
        } catch (Exception e){

        }
    }
}
