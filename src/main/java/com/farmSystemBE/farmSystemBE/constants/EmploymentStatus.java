package com.farmSystemBE.farmSystemBE.constants;

public enum EmploymentStatus {
    EMPLOYEED("EMPLOYEED"),RESIGNED("EMPLOYEED"),ONBOARDING_PENDING("ONBOARDING_PENDING");
    private final String value;
    private EmploymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
