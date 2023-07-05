package com.farmSystemBE.farmSystemBE.repository;

import com.farmSystemBE.farmSystemBE.entity.Attendence;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface AttendenceRepo extends CrudRepository<Attendence,Long> {

    @Query(value = "select * from attendence where date = ?1",nativeQuery = true)
    public List<Attendence> findByDate(LocalDate date);
    @Query(value = "select * from attendence where employee_id = ?1 and date = ?2",nativeQuery = true)
    public List<Attendence> findByEmployeeIdAndDate(long id , LocalDate date);

    @Transactional
    @Query(value = "delete from attendence where date = ?1 and employee_id= ?2",nativeQuery = true)
    public void deleteAttendenceByDateAndEmployeeId(LocalDate date, long emloyeeId);
}
