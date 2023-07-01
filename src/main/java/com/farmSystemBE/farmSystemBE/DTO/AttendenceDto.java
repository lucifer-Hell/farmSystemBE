package com.farmSystemBE.farmSystemBE.DTO;

import com.farmSystemBE.farmSystemBE.constants.Shift;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NonNull;

import java.sql.Date;
import java.util.List;
@Data
public class AttendenceDto {
    long employeeId;
    @NonNull
    Date date;
    @NonNull
    Shift shift;
}
