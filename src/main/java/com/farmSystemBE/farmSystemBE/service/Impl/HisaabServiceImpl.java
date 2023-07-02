package com.farmSystemBE.farmSystemBE.service.Impl;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;
import com.farmSystemBE.farmSystemBE.DTO.DateWithShiftsDto;
import com.farmSystemBE.farmSystemBE.DTO.PaymentReportDto;
import com.farmSystemBE.farmSystemBE.DTO.PaymentReportRowDto;
import com.farmSystemBE.farmSystemBE.constants.Shift;
import com.farmSystemBE.farmSystemBE.service.AttendenceService;
import com.farmSystemBE.farmSystemBE.service.EmployeeService;
import com.farmSystemBE.farmSystemBE.service.HisaabService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class HisaabServiceImpl implements HisaabService {
    @Autowired
    AttendenceService attendenceService;
    @Autowired
    EmployeeService employeeService;

    @Override
    public PaymentReportDto generatePaymentReport(LocalDate fromDate, LocalDate toDate) {
        // get list of all employees worked between from date and to date
        Set<Long> employeeIds= findEmployeePresentInDateRange(fromDate,toDate);
        // for each employee find data generate data related to its payment
        List<PaymentReportRowDto> paymentReportRows= employeeIds.stream().map(id -> {
            double employeeSalary=employeeService.getEmployeeDetails(id).getSalary();
            Map<LocalDate, Set<Shift>> dateShiftMap=new HashMap<>();
            fromDate.datesUntil(toDate).forEach(date -> {
                List<AttendenceDto> attendenceDtos=attendenceService.getAttendenceDetailByEmployeeIdAndDate(id,date);
                // generate dateShift map
                attendenceDtos.forEach(attendenceDto ->{
                    if(dateShiftMap.containsKey(attendenceDto.getDate())){
                        dateShiftMap.get(attendenceDto.getDate()).add(attendenceDto.getShift());
                    }else{
                        HashSet<Shift> shifts=new HashSet<>();
                        shifts.add(attendenceDto.getShift());
                        dateShiftMap.put(attendenceDto.getDate(),shifts);
                    }
                });
            });
            return PaymentReportRowDto.builder()
                    .employeeId(id)
                    .dateShiftMap(dateShiftMap)
                    .totalWage(employeeSalary*getTotalShifts(dateShiftMap))
                    .build();
        }).toList();
        return PaymentReportDto.builder()
                .from(fromDate)
                .to(toDate)
                .paymentReportRows(paymentReportRows)
                .build();
    }

    private int getTotalShifts(Map<LocalDate, Set<Shift>> dateShiftMap) {
        int totalShifts=0;
        for(LocalDate date: dateShiftMap.keySet())
            for(Shift shift:dateShiftMap.get(date))
                totalShifts++;
        return totalShifts;
    }

    private Set<Long> findEmployeePresentInDateRange(LocalDate fromDate, LocalDate toDate) {
        Set<Long> employeeIds=new HashSet<>();
        fromDate.datesUntil(toDate).forEach(date -> {
            attendenceService.getAttendenceDetailByDate(date).forEach(attendenceDto -> {
                        employeeIds.add(attendenceDto.getEmployeeId());
                    });
        });
        return employeeIds;
    }
}