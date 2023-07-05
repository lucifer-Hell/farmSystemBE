package com.farmSystemBE.farmSystemBE.DTO;

import com.farmSystemBE.farmSystemBE.constants.Shift;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class EmployeeShiftDto {
    @NonNull @NotBlank
    long employeeId;
    @NonNull @NotBlank
    Shift shift;
}
