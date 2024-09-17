package com.financeTracker.finance.tracker.entities.enums;

public enum Roles {
    ADMIN(1L),
    USER(2L);

    private final Long id;

    Roles(Long id) {
        this.id = id;
    }
}
