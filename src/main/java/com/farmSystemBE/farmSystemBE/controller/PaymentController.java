package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.DTO.PaymentReportDto;
import com.farmSystemBE.farmSystemBE.service.HisaabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    HisaabService hisaabService;
    @GetMapping("/generateReport")
    PaymentReportDto generatePaymentReport(@RequestParam("from")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                           @RequestParam("to")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to ){
        return hisaabService.generatePaymentReport(from,to);
    }
}
