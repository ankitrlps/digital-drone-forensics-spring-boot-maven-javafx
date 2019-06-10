package dforensics.dji.entity;

import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(
        name = "DetailsColumn.fetchDetails",
        query = "SELECT * from details_column",
        resultClass = DetailsColumn.class
)

@Data
@Entity
public class DetailsColumn {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String timestamp;
    @Column(name = "d_street")
    private String dStreet;
    @Column(name = "d_city_street")
    private String dCityPart;
    @Column(name = "d_city")
    private String dCity;
    @Column(name = "d_area")
    private String dArea;
    @Column(name = "d_is_fav")
    private String dIsFav;
    @Column(name = "d_is_new")
    private String dIsNew;
    @Column(name = "d_need_upload")
    private String dNeedUpload;
    @Column(name = "d_record_line_count")
    private String dRecordLineCount;
    @Column(name = "d_timestamp")
    private String dTimeStamp;
    @Column(name = "d_latitude")
    private String dLatitude;
    @Column(name = "d_longitude")
    private String dLongitude;
    @Column(name = "d_total_distance")
    private String dTotalDistance;
    @Column(name = "d_total_time")
    private String dTotalTime;
    @Column(name = "d_max_height")
    private String dMaxHeight;
    @Column(name = "d_max_h_speed")
    private String dMaxHSpeed;
    @Column(name = "d_max_v_speed")
    private String dMaxVSpeed;
    @Column(name = "d_photo_num")
    private String dPhotoNum;
    @Column(name = "d_video_time")
    private String dVideoTime;
    @Column(name = "d_take_off_altitude")
    private String dTakeOffAltitude;
    @Column(name = "d_drone_type")
    private String dDroneType;
    @Column(name = "d_active_timestamp")
    private String dActiveTimestamp;
    @Column(name = "d_aircraft_name")
    private String dAircraftName;
    @Column(name = "d_air_sn_bytes")
    private String dAirSnBytes;
    @Column(name = "d_camera_sn")
    private String dCameraSn;
    @Column(name = "d_rc_sn")
    private String dRcSn;
    @Column(name = "d_battery_sn")
    private String dBatterySn;
    @Column(name = "d_app_type")
    private String dAppType;
    @Column(name = "d_app_version")
    private String dAppVersion;

    public DetailsColumn(){}

    public DetailsColumn(String timestamp, String dStreet, String dCityPart, String dCity, String dArea, String dIsFav, String dIsNew, String dNeedUpload, String dRecordLineCount, String dTimeStamp, String dLatitude, String dLongitude, String dTotalDistance, String dTotalTime, String dMaxHeight, String dMaxHSpeed, String dMaxVSpeed, String dPhotoNum, String dVideoTime, String dTakeOffAltitude, String dDroneType, String dActiveTimestamp, String dAircraftName, String dAirSnBytes, String dCameraSn, String dRcSn, String dBatterySn, String dAppType, String dAppVersion) {
        this.timestamp = timestamp;
        this.dStreet = dStreet;
        this.dCityPart = dCityPart;
        this.dCity = dCity;
        this.dArea = dArea;
        this.dIsFav = dIsFav;
        this.dIsNew = dIsNew;
        this.dNeedUpload = dNeedUpload;
        this.dRecordLineCount = dRecordLineCount;
        this.dTimeStamp = dTimeStamp;
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;
        this.dTotalDistance = dTotalDistance;
        this.dTotalTime = dTotalTime;
        this.dMaxHeight = dMaxHeight;
        this.dMaxHSpeed = dMaxHSpeed;
        this.dMaxVSpeed = dMaxVSpeed;
        this.dPhotoNum = dPhotoNum;
        this.dVideoTime = dVideoTime;
        this.dTakeOffAltitude = dTakeOffAltitude;
        this.dDroneType = dDroneType;
        this.dActiveTimestamp = dActiveTimestamp;
        this.dAircraftName = dAircraftName;
        this.dAirSnBytes = dAirSnBytes;
        this.dCameraSn = dCameraSn;
        this.dRcSn = dRcSn;
        this.dBatterySn = dBatterySn;
        this.dAppType = dAppType;
        this.dAppVersion = dAppVersion;
    }
}
