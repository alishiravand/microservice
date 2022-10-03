package com.rastech.fraud.service;

import com.rastech.fraud.domain.FraudCheckHistory;
import com.rastech.fraud.repository.FraudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Ali Shiravand, 10/3/22 5:40 PM
 */
@Service
public record FraudService(FraudRepository fraudRepository) {
    public FraudCheckHistory store(Integer customerId) {
        FraudCheckHistory fraudCheckHistory =
                new FraudCheckHistory(customerId, new Random().nextBoolean());
        return fraudRepository.save(fraudCheckHistory);
    }

    public List<FraudCheckHistory> getAll() {
        return fraudRepository.findAll();
    }

    public boolean isFraudulentCustomer(Integer customerId) {
        return new Random().nextBoolean();
    }
}
