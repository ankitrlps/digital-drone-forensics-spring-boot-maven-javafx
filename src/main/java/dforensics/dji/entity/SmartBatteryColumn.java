package dforensics.dji.entity;

import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.domain.TimeAndTwoColumn;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(
        name = "SmartBatteryColumn.fetchTimeAndBattery",
        query = "SELECT id, timestamp, sb_Battery AS column from smart_battery_column",
        resultClass = TimeAndColumn.class
)

@NamedNativeQuery(
        name = "SmartBatteryColumn.fetchBatteryAndVoltage",
        query = "select id, timestamp , sb_battery AS column1, sb_voltage AS column2 from smart_battery_column",
        resultClass = TimeAndTwoColumn.class
)

@Data
@Entity
public class SmartBatteryColumn {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String timestamp;
    private String sbUsefulTime;
    private String sbGoHomeTime;
    private String sbLandTime;
    private String sbGoHomeBattery;
    private String sbLandBattery;
    private String sbSafeFlyRadius;
    private String sbVolumeConsume;
    private String sbStatus;
    private String sbStatusRaw;
    private String sbGoHomeStatus;
    private String sbGoHomeStatusRaw;
    private String sbGoHomeCountdown;
    private String sbVoltage;
    private String sbBattery;
    private String sbLowWarning;
    private String sbLowWarningGoHome;
    private String sbSeriousLowWarning;
    private String sbSeriousLowWarningLanding;
    private String sbVoltagePercent;

    public SmartBatteryColumn(){}

    public SmartBatteryColumn(String timestamp, String sbUsefulTime, String sbGoHomeTime, String sbLandTime, String sbGoHomeBattery, String sbLandBattery, String sbSafeFlyRadius, String sbVolumeConsume, String sbStatus, String sbStatusRaw, String sbGoHomeStatus, String sbGoHomeStatusRaw, String sbGoHomeCountdown, String sbVoltage, String sbBattery, String sbLowWarning, String sbLowWarningGoHome, String sbSeriousLowWarning, String sbSeriousLowWarningLanding, String sbVoltagePercent) {
        this.timestamp = timestamp;
        this.sbUsefulTime = sbUsefulTime;
        this.sbGoHomeTime = sbGoHomeTime;
        this.sbLandTime = sbLandTime;
        this.sbGoHomeBattery = sbGoHomeBattery;
        this.sbLandBattery = sbLandBattery;
        this.sbSafeFlyRadius = sbSafeFlyRadius;
        this.sbVolumeConsume = sbVolumeConsume;
        this.sbStatus = sbStatus;
        this.sbStatusRaw = sbStatusRaw;
        this.sbGoHomeStatus = sbGoHomeStatus;
        this.sbGoHomeStatusRaw = sbGoHomeStatusRaw;
        this.sbGoHomeCountdown = sbGoHomeCountdown;
        this.sbVoltage = sbVoltage;
        this.sbBattery = sbBattery;
        this.sbLowWarning = sbLowWarning;
        this.sbLowWarningGoHome = sbLowWarningGoHome;
        this.sbSeriousLowWarning = sbSeriousLowWarning;
        this.sbSeriousLowWarningLanding = sbSeriousLowWarningLanding;
        this.sbVoltagePercent = sbVoltagePercent;
    }
}
