package com.farmSystemBE.farmSystemBE.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @GetMapping
    ResponseEntity generatePaymentReport(Date from, Date to ){
        return ResponseEntity.ok("payment report ");
    }
}
