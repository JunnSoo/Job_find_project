package com.project.it_job.util.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.it_job.dto.InforEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaHelper {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendKafkaEmailRegister(String topics, InforEmailDTO inforEmailDTO) {
        try {
            if (inforEmailDTO == null)
                throw new Exception("inforEmailDTO is null");
            String jsonString = objectMapper.writeValueAsString(inforEmailDTO);
            kafkaTemplate.send(topics, jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
