package com.farmSystemBE.farmSystemBE.DTO;

import com.farmSystemBE.farmSystemBE.constants.EmployeeType;
import com.farmSystemBE.farmSystemBE.constants.EmploymentStatus;
import com.farmSystemBE.farmSystemBE.constants.Gender;
import jakarta.annotation.Nonnull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NonNull
    String firstName;
    @NonNull
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
