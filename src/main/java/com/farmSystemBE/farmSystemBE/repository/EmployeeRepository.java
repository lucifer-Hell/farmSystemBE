package com.farmSystemBE.farmSystemBE.repository;

import com.farmSystemBE.farmSystemBE.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query(value = "SELECT * FROM employee e WHERE e.first_name = ?1 AND e.last_name= ?2",nativeQuery = true)
    List<Employee> findEmployeesByName(String firstName, String lastName);

    @Query(value = "SELECT * FROM employee e WHERE e.id= ?1",nativeQuery = true)
    Employee findEmployeeByEmployeeId(long id);
}
