package com.farmSystemBE.farmSystemBE.DTO;

import com.farmSystemBE.farmSystemBE.constants.EmployeeType;
import com.farmSystemBE.farmSystemBE.constants.EmploymentStatus;
import com.farmSystemBE.farmSystemBE.constants.Gender;
import jakarta.annotation.Nonnull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class EmployeeDto {
    String firstName;
    String lastName;
    String mobileNumber;
    @NonNull
    Gender gender;
    double salary;
    @NonNull
    EmployeeType emplyoeeType;
    @NonNull
    EmploymentStatus employmentStatus;
}
