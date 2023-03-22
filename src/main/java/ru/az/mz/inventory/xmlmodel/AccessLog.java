package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class AccessLog {

    private String logDate;

    @XmlElement(name = "LOGDATE")
    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "logDate='" + logDate + '\'' +
                '}';
    }
}
