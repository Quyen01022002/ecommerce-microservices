package com.ecommerce.microservices.common.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class KafkaSender {

    final KafkaTemplate<Integer, Object> kafkaTemplate;

    @Async("kafkaSenderExecutorThreadPool")
    public void publishToKafka(String topic, Optional<UUID> key, Object payload) {
        if (!Objects.isNull(key)) {
            key.ifPresent(uuid -> kafkaTemplate.send(topic, uuid.toString().hashCode(), new BaseMessage(payload, Strings.EMPTY)));
        } else {
            kafkaTemplate.send(topic, new BaseMessage(payload, Strings.EMPTY));
        }
    }}
