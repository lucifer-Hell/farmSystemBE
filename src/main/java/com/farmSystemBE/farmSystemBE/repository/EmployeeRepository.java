package com.farmSystemBE.farmSystemBE.repository;

import com.farmSystemBE.farmSystemBE.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
