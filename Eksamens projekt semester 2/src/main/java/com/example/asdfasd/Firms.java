package com.example.asdfasd;

public class Firms {
    private String FirmID;
    private String FirmName;


    @Override
    public String toString() {
        return "Firms{" +
                "FirmID='" + FirmID + '\'' +
                ", FirmName='" + FirmName + '\'' +
                '}';
    }

    public Firms(String firmID, String firmName) {
        FirmID = firmID;
        FirmName = firmName;
    }

    public Firms() {
    }

    public String getFirmID() {
        return FirmID;
    }

    public void setFirmID(String firmID) {
        FirmID = firmID;
    }

    public String getFirmName() {
        return FirmName;
    }

    public void setFirmName(String firmName) {
        FirmName = firmName;
    }
}
