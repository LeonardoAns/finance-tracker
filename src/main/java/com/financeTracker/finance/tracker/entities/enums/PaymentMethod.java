package com.financeTracker.finance.tracker.entities.enums;

public enum PaymentMethod {
    CREDIT_CARD(1L),
    DEBIT_CARD(2L),
    PIX(3L),
    CASH(4L);

    private final Long id;

    PaymentMethod(Long id) {
        this.id = id;
    }
}
