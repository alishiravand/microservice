package com.rastech.fraud.repository;

import com.rastech.fraud.domain.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ali Shiravand, 10/3/22 5:39 PM
 */
public interface FraudRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
