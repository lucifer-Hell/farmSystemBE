package com.farmSystemBE.farmSystemBE.constants;

public enum Shift {
    MORNING("MORNING"),AFTERNOON("AFTERNOON"),EVENING("EVENING");
    private final String value;
    private Shift(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
