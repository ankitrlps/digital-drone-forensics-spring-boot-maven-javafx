package dforensics.dji.entity;

import dforensics.dji.domain.TimeAndThreeColumns;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(
        name = "GimbalColumn.fetchTimeAndGimbalPRY",
        query = "SELECT id, timestamp, g_pitch AS column1, g_roll AS column2, g_yaw AS column3 from gimbal_column",
        resultClass = TimeAndThreeColumns.class
)

@Data
@Entity
public class GimbalColumn {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String timestamp;
    private String gPitch;
    private String gRoll;
    private String gYaw;
    private String gMode;
    private String gModeRaw;
    private String gRollAdjust;
    private String gPitchAdjust;
    private String gYawAngle;
    private String gIsAutoCalibration;
    private String gAutoCalibrationResult;
    private String gisPitchInLimit;
    private String gisRollInLimit;
    private String gisYawInLimit;
    private String gIsTopPos;
    private String gIsStuck;
    private String gVersion;
    private String gIsSingleClick;
    private String gIsDoubleClick;
    private String gIsTripleClick;
    private String gTimeStamp;

    public GimbalColumn(){}

    public GimbalColumn(String timestamp, String gPitch, String gRoll, String gYaw, String gMode, String gModeRaw, String gRollAdjust, String gPitchAdjust, String gYawAngle, String gIsAutoCalibration, String gAutoCalibrationResult, String gisPitchInLimit, String gisRollInLimit, String gisYawInLimit, String gIsTopPos, String gIsStuck, String gVersion, String gIsSingleClick, String gIsDoubleClick, String gIsTripleClick, String gTimeStamp) {
        this.timestamp = timestamp;
        this.gPitch = gPitch;
        this.gRoll = gRoll;
        this.gYaw = gYaw;
        this.gMode = gMode;
        this.gModeRaw = gModeRaw;
        this.gRollAdjust = gRollAdjust;
        this.gPitchAdjust = gPitchAdjust;
        this.gYawAngle = gYawAngle;
        this.gIsAutoCalibration = gIsAutoCalibration;
        this.gAutoCalibrationResult = gAutoCalibrationResult;
        this.gisPitchInLimit = gisPitchInLimit;
        this.gisRollInLimit = gisRollInLimit;
        this.gisYawInLimit = gisYawInLimit;
        this.gIsTopPos = gIsTopPos;
        this.gIsStuck = gIsStuck;
        this.gVersion = gVersion;
        this.gIsSingleClick = gIsSingleClick;
        this.gIsDoubleClick = gIsDoubleClick;
        this.gIsTripleClick = gIsTripleClick;
        this.gTimeStamp = gTimeStamp;
    }
}
