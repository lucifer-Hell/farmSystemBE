package com.farmSystemBE.farmSystemBE.service;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;
import com.farmSystemBE.farmSystemBE.DTO.EmployeeShiftDto;

import java.time.LocalDate;
import java.util.List;

public interface AttendenceService {
    public List<AttendenceDto> getAttendenceDetailByDate(LocalDate date);
    public void markAttendence(AttendenceDto attendenceDto);
    public void updateAttendenceInBulk(LocalDate date, List<EmployeeShiftDto> employeeShiftList);
    public List<AttendenceDto>getAttendenceDetailByEmployeeIdAndDate(long employeeId, LocalDate date);
}
