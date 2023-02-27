package com.yt.project.common;

public enum InterfaceInfoStatusEnum {

    OFFLINE("下线", 0),
    ONLINE("上线", 1);

    private final String status;

    private final int value;

    InterfaceInfoStatusEnum(String status, int value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public int getValue() {
        return value;
    }
}
