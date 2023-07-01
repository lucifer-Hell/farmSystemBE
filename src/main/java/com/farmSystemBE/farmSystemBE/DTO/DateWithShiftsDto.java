package com.farmSystemBE.farmSystemBE.DTO;

import com.farmSystemBE.farmSystemBE.constants.Shift;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
@Builder
public class DateWithShiftsDto {
    Date date;
    List<Shift> shiftList;
}
