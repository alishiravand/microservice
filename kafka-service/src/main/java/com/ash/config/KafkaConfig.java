package com.ash.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Ali Shiravand, 9/28/22 7:00 PM
 */
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic ashTopic() {
        return TopicBuilder.name("ash-topic").build();
    }
}
