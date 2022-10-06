package com.rastech.fraud.controller;

import com.rastech.fraud.domain.FraudCheckHistory;
import com.rastech.fraud.service.FraudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ali Shiravand, 10/3/22 5:41 PM
 */
@RestController
@RequestMapping("api/v1/fraud")
public record FraudController(FraudService fraudService) {

    @GetMapping(path = "{customerId}")
    public ResponseEntity<Boolean> store(@PathVariable Integer customerId) {
        return ResponseEntity.ok(fraudService.store(customerId).getFraudster());
    }

    @GetMapping
    public ResponseEntity<List<FraudCheckHistory>> getAll() {
        return ResponseEntity.ok(fraudService.getAll());
    }
}
