package com.farmSystemBE.farmSystemBE.entity;

import com.farmSystemBE.farmSystemBE.constants.Shift;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Attendence {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long id;
    @Nonnull
    long employeeId;
    @Nonnull
    Date date;
    @Nonnull
    Shift shift;
}
