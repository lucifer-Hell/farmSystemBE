package com.farmSystemBE.farmSystemBE.DTO;

import com.farmSystemBE.farmSystemBE.constants.Shift;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
@Data
public class AttendenceDto {
    @NonNull @NotBlank
    long employeeId;
    @NonNull @NotBlank
    LocalDate date;
    @NonNull @NotBlank
    Shift shift;
}
