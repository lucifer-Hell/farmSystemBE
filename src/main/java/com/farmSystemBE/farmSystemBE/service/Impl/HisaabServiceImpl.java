package com.farmSystemBE.farmSystemBE.service.Impl;

import com.farmSystemBE.farmSystemBE.DTO.PaymentReportDto;
import com.farmSystemBE.farmSystemBE.DTO.PaymentReportRowDto;
import com.farmSystemBE.farmSystemBE.service.AttendenceService;
import com.farmSystemBE.farmSystemBE.service.EmployeeService;
import com.farmSystemBE.farmSystemBE.service.HisaabService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class HisaabServiceImpl implements HisaabService {
    @Autowired
    AttendenceService attendenceService;
    @Autowired
    EmployeeService employeeService;

    @Override
    public PaymentReportDto generatePaymentReport(LocalDate fromDate, LocalDate toDate) {
        // get list of all employees worked between from date and to date
        Set<Long> employeeIds=findEmployeePresentInDate(fromDate,toDate);
        List<PaymentReportRowDto> paymentReportRowDto= employeeIds.stream().map(id -> {
            return new PaymentReportRowDto();
        }).toList();
        return PaymentReportDto.builder()
                .from(fromDate)
                .to(toDate)
                .build();
    }

    private Set<Long> findEmployeePresentInDate(LocalDate fromDate, LocalDate toDate) {
        Set<Long> employeeIds=new HashSet<>();
        fromDate.datesUntil(toDate).forEach(date -> {
            attendenceService.getAttendenceDetailByDate(date).forEach(attendenceDto -> {
                        employeeIds.add(attendenceDto.getEmployeeId());
                    });
        });
        return employeeIds;
    }
}
