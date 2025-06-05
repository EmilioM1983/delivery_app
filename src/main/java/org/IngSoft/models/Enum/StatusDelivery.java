package org.IngSoft.models.Enum;

public enum StatusDelivery {
    Delivered, InProgress;

    public static StatusDelivery fromString(String value) {
        for (StatusDelivery status : StatusDelivery.values()) {
            if (status.name().equalsIgnoreCase(value.replace(" ", ""))) {
                return status;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + value);
    }
}

