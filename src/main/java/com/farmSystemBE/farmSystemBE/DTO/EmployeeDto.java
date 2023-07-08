package com.farmSystemBE.farmSystemBE.DTO;

import com.farmSystemBE.farmSystemBE.constants.EmployeeType;
import com.farmSystemBE.farmSystemBE.constants.EmploymentStatus;
import com.farmSystemBE.farmSystemBE.constants.Gender;
import jakarta.annotation.Nonnull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class EmployeeDto {
    long id;
    @NonNull @NotBlank
    String firstName;
    @NonNull @NotBlank
    String lastName;
    String mobileNumber;
    @NonNull @NotBlank
    Gender gender;
    double salary;
    @NonNull @NotBlank
    EmployeeType emplyoeeType;
    @NonNull @NotBlank
    EmploymentStatus employmentStatus;
}
