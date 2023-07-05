package com.farmSystemBE.farmSystemBE.service.Impl;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;
import com.farmSystemBE.farmSystemBE.mapper.AttendenceMapper;
import com.farmSystemBE.farmSystemBE.repository.AttendenceRepo;
import com.farmSystemBE.farmSystemBE.service.AttendenceService;
import com.farmSystemBE.farmSystemBE.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class AttendenceServiceImpl implements AttendenceService {
    @Autowired
    AttendenceRepo attendenceRepo;
    @Autowired
    AttendenceMapper attendenceMapper;
    @Autowired
    EmployeeService employeeService;
    @Override
    public List<AttendenceDto> getAttendenceDetailByDate(LocalDate date) {
        return attendenceRepo.
                findByDate(date).
                stream().
                map(attendence -> attendenceMapper.attendenceToAttendenceDto(attendence))
                .collect(Collectors.toList());
    }

    @Override
    public void markAttendence(LocalDate date, List<AttendenceDto> attendenceDtoList) {
        // delete old attendence
        attendenceRepo.deleteAttendenceByDate(date);
        // add new attendence
        attendenceDtoList.stream()
                .filter(attendenceDto -> {
                    return employeeService.getEmployeeDetails(attendenceDto.getEmployeeId())!=null;
                })
                .map(attendenceDto -> {
                    return attendenceMapper.attendenceDtoToAttendence(attendenceDto);
                }).forEach(attendence -> {
                    attendenceRepo.save(attendence);
                });
    }

    @Override
    public List<AttendenceDto> getAttendenceDetailByEmployeeIdAndDate(long employeeId, LocalDate date) {
        return attendenceRepo.findByEmployeeIdAndDate(employeeId,date)
                .stream().map(attendence ->attendenceMapper.attendenceToAttendenceDto(attendence))
                .collect(Collectors.toList());
    }
}
