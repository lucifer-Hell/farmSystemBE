package com.farmSystemBE.farmSystemBE.entity;

import com.farmSystemBE.farmSystemBE.constants.Shift;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Attendence {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    long id;
    @Nonnull
    long employeeId;
    @Nonnull
    Date date;
    @Nonnull
    String shift;
}
