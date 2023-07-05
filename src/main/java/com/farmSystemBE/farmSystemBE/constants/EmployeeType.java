package com.farmSystemBE.farmSystemBE.constants;

import jakarta.persistence.Converter;


public enum EmployeeType {
    MUNSHI("MUNSHI"),LEVER("LEVER"),ADMIN("ADMIN");
    private final String value;
    private EmployeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
