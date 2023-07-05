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

    public List<Attendence> findByDate(LocalDate date);
    public List<Attendence> findByEmployeeId(long id);
    public List<Attendence> findByEmployeeIdAndDate(long id , LocalDate date);

    @Modifying
    @Transactional
    @Query(value = "delete from attendence where date = ?1",nativeQuery = true)
    public void deleteAttendenceByDate(LocalDate date);
}
