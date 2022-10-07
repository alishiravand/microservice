package com.rastech.fraud.service;

import com.rastech.fraud.domain.FraudCheckHistory;
import com.rastech.fraud.repository.FraudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author Ali Shiravand, 10/3/22 5:40 PM
 */
@Service
public record FraudService(FraudRepository fraudRepository, RestTemplate restTemplate) {
    public FraudCheckHistory store(Integer customerId) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        FraudCheckHistory fraudCheckHistory =
                new FraudCheckHistory(customerId, new Random().nextBoolean());
        fraudCheckHistory = fraudRepository.save(fraudCheckHistory);
        Message message = new Message(fraudCheckHistory.toString());

        ResponseEntity<Message> result = restTemplate.postForEntity(
                "http://NOTIFICATION-SERVICE/api/v1/message",
                message,
                Message.class);
       /* if (!result.getStatusCode().equals(HttpStatus.OK))
            throw new IllegalStateException("");*/
        return fraudCheckHistory;
    }

    public List<FraudCheckHistory> getAll() {
        return fraudRepository.findAll();
    }

    public boolean isFraudulentCustomer(Integer customerId) {
        return new Random().nextBoolean();
    }
}
