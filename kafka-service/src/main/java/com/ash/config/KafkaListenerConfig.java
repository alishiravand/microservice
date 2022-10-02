package com.ash.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author Ali Shiravand, 9/28/22 10:14 PM
 */
@Component
public class KafkaListenerConfig {
    Logger logger = Logger.getLogger(KafkaListenerConfig.class.getSimpleName());

    @KafkaListener(
            topics = "ash-topic",
            groupId = "groupId")
    void listen(String data) {
        logger.info("Listend by Ash listener: " + data);
    }
}
