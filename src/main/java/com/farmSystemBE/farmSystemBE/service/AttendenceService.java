package com.farmSystemBE.farmSystemBE.service;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AttendenceService {
    public List<AttendenceDto> getAttendenceDetailByDate(LocalDate date);
    public void markAttendence(LocalDate date,List<AttendenceDto> attendenceDtoList);

    public List<AttendenceDto>getAttendenceDetailByEmployeeIdAndDate(long employeeId, LocalDate date);
}
