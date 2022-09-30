package org.orakzai.lab.shop.domain.business.order.model.orderstatus;

public enum OrderStatus {
    ORDERED("ordered"),
    PROCESSED("processed"),
    DELIVERED("delivered"),
    REFUNDED("refunded");

    private String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
