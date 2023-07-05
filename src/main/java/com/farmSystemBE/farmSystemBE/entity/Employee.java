package com.farmSystemBE.farmSystemBE.entity;

import com.farmSystemBE.farmSystemBE.constants.EmployeeType;
import com.farmSystemBE.farmSystemBE.constants.EmploymentStatus;
import com.farmSystemBE.farmSystemBE.constants.Gender;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Entity
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nonnull
    long id;
    String firstName;
    String lastName;
    String mobileNumber;
    @Nonnull
    String gender;
    double salary;
    @Nonnull
    String emplyoeeType;
    @NonNull
    String employmentStatus;
}
