package com.farmSystemBE.farmSystemBE.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReportDto {
    LocalDate from;
    LocalDate to;
    List<PaymentReportRowDto> paymentReportRows;
}
