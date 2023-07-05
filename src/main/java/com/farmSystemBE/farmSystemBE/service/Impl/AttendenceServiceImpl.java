package com.farmSystemBE.farmSystemBE.service.Impl;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;
import com.farmSystemBE.farmSystemBE.DTO.EmployeeShiftDto;
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
    public void updateAttendenceInBulk(LocalDate date, List<EmployeeShiftDto> employeeShiftList) {
        employeeShiftList.forEach(employeeShiftDto -> {
                    // delete old attendence
                    attendenceRepo.deleteAttendenceByDateAndEmployeeId(date,employeeShiftDto.getEmployeeId());
                });
        employeeShiftList.forEach(employeeShiftDto -> {
                    // update new attendence
                    markAttendence(new AttendenceDto(employeeShiftDto.getEmployeeId(),date,employeeShiftDto.getShift()));
                });
    }

    @Override
    public void markAttendence(AttendenceDto attendenceDto) {
        // add new attendence
        if(employeeService.getEmployeeDetails(attendenceDto.getEmployeeId())==null){
            // TODO throw service exception
        }else {
            attendenceRepo.save(attendenceMapper.attendenceDtoToAttendence(attendenceDto));
        }
    }

    @Override
    public List<AttendenceDto> getAttendenceDetailByEmployeeIdAndDate(long employeeId, LocalDate date) {
        return attendenceRepo.findByEmployeeIdAndDate(employeeId,date)
                .stream().map(attendence ->attendenceMapper.attendenceToAttendenceDto(attendence))
                .collect(Collectors.toList());
    }
}
