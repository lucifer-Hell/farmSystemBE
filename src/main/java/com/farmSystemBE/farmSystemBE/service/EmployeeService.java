package com.farmSystemBE.farmSystemBE.service;

import com.farmSystemBE.farmSystemBE.DTO.EmployeeDto;

import java.net.HttpRetryException;
import java.util.List;

public interface EmployeeService {
    void addEmployee(EmployeeDto employeeDto) throws HttpRetryException;
    EmployeeDto updateEmployee(long employeeId,EmployeeDto employeeDto);
    void deleteEmployeeById(long employeeId);
    EmployeeDto getEmployeeDetails(long employeeId);

    List<EmployeeDto> getAllEmployeeDetails();
}
