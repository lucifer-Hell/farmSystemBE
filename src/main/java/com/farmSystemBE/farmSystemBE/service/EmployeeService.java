package com.farmSystemBE.farmSystemBE.service;

import com.farmSystemBE.farmSystemBE.DTO.EmployeeDto;

public interface EmployeeService {
    void addEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(long employeeId,EmployeeDto employeeDto);
    void deleteEmployeeById(long employeeId);
    EmployeeDto getEmployeeDetails(long employeeId);
}
