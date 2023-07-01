package com.farmSystemBE.farmSystemBE.service;

import com.farmSystemBE.farmSystemBE.DTO.PaymentReportDto;

import java.time.LocalDate;
import java.util.Date;

public interface HisaabService {
    public PaymentReportDto generatePaymentReport(LocalDate fromDate, LocalDate toDate);
}
