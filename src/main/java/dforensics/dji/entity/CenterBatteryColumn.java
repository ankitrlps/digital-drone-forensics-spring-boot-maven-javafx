package dforensics.dji.entity;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndColumn;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(
        name = "CenterBatteryColumn.fetchProductDate",
        query = "SELECT id, cb_product_date AS column from center_battery_column",
        resultClass = SingleColumn.class
)

@Data
@Entity
public class CenterBatteryColumn {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String timestamp;
    private String cbRelativeCapacity;
    private String cbCurrentPV;
    private String cbCurrentCapacity;
    private String cbFullCapacity;
    private String cbLife;
    private String cbLoopNum;
    private String cbErrorType;
    private String cbCurrent;
    private String cbVoltageCell1;
    private String cbVoltageCell2;
    private String cbVoltageCell3;
    private String cbVoltageCell4;
    private String cbVoltageCell5;
    private String cbVoltageCell6;
    private String cbSerialNo;
    private String cbProductDate;
    private String cbTemp;
    private String cbConnStatus;
    private String cbTotalStudyCycle;
    private String cbLastStudyCycle;
    private String cbIsNeedStudy;
    private String cbIsBatteryOnCharge;

    public CenterBatteryColumn(){}

    public CenterBatteryColumn(String timestamp, String cbRelativeCapacity, String cbCurrentPV, String cbCurrentCapacity, String cbFullCapacity, String cbLife, String cbLoopNum, String cbErrorType, String cbCurrent, String cbVoltageCell1, String cbVoltageCell2, String cbVoltageCell3, String cbVoltageCell4, String cbVoltageCell5, String cbVoltageCell6, String cbSerialNo, String cbProductDate, String cbTemp, String cbConnStatus, String cbTotalStudyCycle, String cbLastStudyCycle, String cbIsNeedStudy, String cbIsBatteryOnCharge) {
        this.timestamp = timestamp;
        this.cbRelativeCapacity = cbRelativeCapacity;
        this.cbCurrentPV = cbCurrentPV;
        this.cbCurrentCapacity = cbCurrentCapacity;
        this.cbFullCapacity = cbFullCapacity;
        this.cbLife = cbLife;
        this.cbLoopNum = cbLoopNum;
        this.cbErrorType = cbErrorType;
        this.cbCurrent = cbCurrent;
        this.cbVoltageCell1 = cbVoltageCell1;
        this.cbVoltageCell2 = cbVoltageCell2;
        this.cbVoltageCell3 = cbVoltageCell3;
        this.cbVoltageCell4 = cbVoltageCell4;
        this.cbVoltageCell5 = cbVoltageCell5;
        this.cbVoltageCell6 = cbVoltageCell6;
        this.cbSerialNo = cbSerialNo;
        this.cbProductDate = cbProductDate;
        this.cbTemp = cbTemp;
        this.cbConnStatus = cbConnStatus;
        this.cbTotalStudyCycle = cbTotalStudyCycle;
        this.cbLastStudyCycle = cbLastStudyCycle;
        this.cbIsNeedStudy = cbIsNeedStudy;
        this.cbIsBatteryOnCharge = cbIsBatteryOnCharge;
    }
}
