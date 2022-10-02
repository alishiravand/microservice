package com.ash.service;

import com.ash.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ali Shiravand, 9/28/22 8:54 PM
 */
@Service
public class MessageService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MessageService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Message message) {
        kafkaTemplate.send("ash-topic", message.toString());
    }

    public List<Message> findAll() {
        return Arrays.asList(
                new Message("10"),
                new Message("80"));
    }
}
