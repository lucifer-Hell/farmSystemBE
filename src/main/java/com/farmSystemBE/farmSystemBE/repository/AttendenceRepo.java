package com.farmSystemBE.farmSystemBE.repository;

import com.farmSystemBE.farmSystemBE.constants.Shift;
import com.farmSystemBE.farmSystemBE.entity.Attendence;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AttendenceRepo extends CrudRepository<Attendence,Long> {

    public List<Attendence> findByDate(LocalDate date);
    public List<Attendence> findByEmployeeId(long id);
    public Attendence findByEmployeeIdAndDateAndShift(long id , LocalDate date, Shift shift);

    public void deleteAttendenceByDate(LocalDate date);
}
