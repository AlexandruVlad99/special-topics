package com.todolistapplicationspecialtopics.model;

public enum Status {
    CREATED, COMPLETED, POSTPONED, DELETED;

    public static boolean isValidStatus(Status status) {
        for (Status validStatus : values()) {
            if (validStatus.equals(status)) {
                return true;
            }
        }
        return false;
    }
}
