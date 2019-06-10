package dforensics.dji.entity;

import dforensics.dji.domain.TimeAndTwoColumn;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery(
        name = "HomeColumn.fetchLatAndLong",
        query = "SELECT id, timestamp, h_latitude AS column1, h_longitude AS column2 from home_column",
        resultClass = TimeAndTwoColumn.class
)

@Data
@Entity
public class HomeColumn {
    @Id
    private int id;
    private String timestamp;
    private String hLatitude;
    private String hLongitude;
    private String hHeight;
    private String hIsHomeRecord;
    private String hGoHomeMode;
    private String hAirHeadDirection;
    private String hisDynamicHomePointEnabled;
    private String hisReachedLimitDistance;
    private String hisReachedLimitHeight;
    private String hisMultipleModeOpen;
    private String hGoHomeStatus;
    private String hHasGoHome;
    private String hCompassCeleStatus;
    private String hIsCompassCeleing;
    private String hIsBeginnerMode;
    private String hIsIOCEnabled;
    private String hIOCEnabled;
    private String hGoHomeHeight;
    private String hCourseLockAngle;
    private String hDRStatus;
    private String hDRRCapacity;
    private String hDRRTime;
    private String hDRFIndex;
    private String hIsFlycInSimMode;
    private String hIsFNMode;
    private String hIsWingBroken;
    private String hIsBigGale;
    private String hIsBGWarning;
    private String hIsCompassInstallErr;
    private String hIFCIBState;
    private String hPaddleState;
    private String hHLStatus;
    private String hUAHeight;
    private String hM1EState;
    private String hM2EState;
    private String hM3EState;
    private String hM4EState;
    private String hM5EState;
    private String hM6EState;
    private String hM7EState;
    private String hM8EState;
    private String hFLHeight;

    public HomeColumn(){}

    public HomeColumn(String timestamp, String hLatitude, String hLongitude, String hHeight, String hIsHomeRecord, String hGoHomeMode, String hAirHeadDirection, String hisDynamicHomePointEnabled, String hisReachedLimitDistance, String hisReachedLimitHeight, String hisMultipleModeOpen, String hGoHomeStatus, String hHasGoHome, String hCompassCeleStatus, String hIsCompassCeleing, String hIsBeginnerMode, String hIsIOCEnabled, String hIOCEnabled, String hGoHomeHeight, String hCourseLockAngle, String hDRStatus, String hDRRCapacity, String hDRRTime, String hDRFIndex, String hIsFlycInSimMode, String hIsFNMode, String hIsWingBroken, String hIsBigGale, String hIsBGWarning, String hIsCompassInstallErr, String hIFCIBState, String hPaddleState, String hHLStatus, String hUAHeight, String hM1EState, String hM2EState, String hM3EState, String hM4EState, String hM5EState, String hM6EState, String hM7EState, String hM8EState, String hFLHeight) {
        this.timestamp = timestamp;
        this.hLatitude = hLatitude;
        this.hLongitude = hLongitude;
        this.hHeight = hHeight;
        this.hIsHomeRecord = hIsHomeRecord;
        this.hGoHomeMode = hGoHomeMode;
        this.hAirHeadDirection = hAirHeadDirection;
        this.hisDynamicHomePointEnabled = hisDynamicHomePointEnabled;
        this.hisReachedLimitDistance = hisReachedLimitDistance;
        this.hisReachedLimitHeight = hisReachedLimitHeight;
        this.hisMultipleModeOpen = hisMultipleModeOpen;
        this.hGoHomeStatus = hGoHomeStatus;
        this.hHasGoHome = hHasGoHome;
        this.hCompassCeleStatus = hCompassCeleStatus;
        this.hIsCompassCeleing = hIsCompassCeleing;
        this.hIsBeginnerMode = hIsBeginnerMode;
        this.hIsIOCEnabled = hIsIOCEnabled;
        this.hIOCEnabled = hIOCEnabled;
        this.hGoHomeHeight = hGoHomeHeight;
        this.hCourseLockAngle = hCourseLockAngle;
        this.hDRStatus = hDRStatus;
        this.hDRRCapacity = hDRRCapacity;
        this.hDRRTime = hDRRTime;
        this.hDRFIndex = hDRFIndex;
        this.hIsFlycInSimMode = hIsFlycInSimMode;
        this.hIsFNMode = hIsFNMode;
        this.hIsWingBroken = hIsWingBroken;
        this.hIsBigGale = hIsBigGale;
        this.hIsBGWarning = hIsBGWarning;
        this.hIsCompassInstallErr = hIsCompassInstallErr;
        this.hIFCIBState = hIFCIBState;
        this.hPaddleState = hPaddleState;
        this.hHLStatus = hHLStatus;
        this.hUAHeight = hUAHeight;
        this.hM1EState = hM1EState;
        this.hM2EState = hM2EState;
        this.hM3EState = hM3EState;
        this.hM4EState = hM4EState;
        this.hM5EState = hM5EState;
        this.hM6EState = hM6EState;
        this.hM7EState = hM7EState;
        this.hM8EState = hM8EState;
        this.hFLHeight = hFLHeight;
    }
}
