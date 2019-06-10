package dforensics.dji.entity;

import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.domain.TimeAndColumn;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(
        name = "OSDColumn.fetchTimeAndAltitude",
        query = "SELECT id, timestamp, altitude AS column from OSDColumn",
        resultClass = TimeAndColumn.class
)
@NamedNativeQuery(
        name = "OSDColumn.fetchTimeAndPitchRollYaw",
        query = "SELECT id, timestamp, pitch AS column1, roll AS column2, yaw AS column3 from OSDColumn",
        resultClass = TimeAndThreeColumns.class
)
@NamedNativeQuery(
        name = "OSDColumn.fetchTimeAndFlyState",
        query = "SELECT id, timestamp, flyc_state_raw AS column from OSDColumn",
        resultClass = TimeAndColumn.class
)
@NamedNativeQuery(
        name = "OSDColumn.fetchTimeAndSatellites",
        query = "SELECT id, timestamp, gps_num AS column from OSDColumn",
        resultClass = TimeAndColumn.class
)

@NamedNativeQuery(
        name = "OSDColumn.fetchAllDetails",
        query = "SELECT * from OSDColumn",
        resultClass = OSDColumn.class
)

@Data
@Entity
public class OSDColumn {

    // 57
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String timestamp;
    private String latitude;
    private String longitude;
    private String height;
    private String altitude;
    @Column(name = "x_speed")
    private String xSpeed;
    @Column(name = "y_speed")
    private String ySpeed;
    @Column(name = "z_speed")
    private String zSpeed;
    private String pitch;
    private String roll;
    private String yaw;
    @Column(name = "flyc_state")
    private String flycState;
    @Column(name = "flyc_state_raw")
    private String flycStateRAW;
    @Column(name = "flyc_command")
    private String flycCommand;
    @Column(name = "flyc_command_raw")
    private String flycCommandRAW;
    @Column(name = "can_iocwork")
    private String canIOCWork;
    @Column(name = "ground_or_sky")
    private String groundOrSky;
    @Column(name = "is_motor_up")
    private String isMotorUp;
    @Column(name = "is_swave_work")
    private String isSwaveWork;
    @Column(name = "go_home_status")
    private String goHomeStatus;
    @Column(name = "go_home_status_raw")
    private String goHomeStatusRAW;
    @Column(name = "is_imu_preheated")
    private String isImuPreheated;
    @Column(name = "is_vision_used")
    private String isVisionUsed;
    @Column(name = "voltage_warning")
    private String voltageWarning;
    @Column(name = "mode_channel")
    private String modeChannel;
    @Column(name = "is_gpsused")
    private String isGPSused;
    @Column(name = "compass_error")
    private String compassError;
    @Column(name = "wave_error")
    private String waveError;
    @Column(name = "gps_level")
    private String gpsLevel;
    @Column(name = "battery_type")
    private String batteryType;
    @Column(name = "is_acceletor_over_range")
    private String isAcceletorOverRange;
    @Column(name = "is_vibrating")
    private String isVibrating;
    @Column(name = "is_barometer_dead_in_air")
    private String isBarometerDeadInAir;
    @Column(name = "is_motor_blocked")
    private String isMotorBlocked;
    @Column(name = "is_not_enough_force")
    private String isNotEnoughForce;
    @Column(name = "is_properller_catapult")
    private String isPropellerCatapult;
    @Column(name = "is_go_home_height_modified")
    private String isGoHomeHeightModified;
    @Column(name = "is_out_of_limit")
    private String isOutOfLimit;
    @Column(name = "gps_num")
    private String gpsNum;
    @Column(name = "flight_action")
    private String flightAction;
    @Column(name = "flight_action_raw")
    private String flightActionRAW;
    @Column(name = "motor_start_failed_cause")
    private String motorStartFailedCause;
    @Column(name = "motor_start_failed_cause_raw")
    private String motorStartFailedCauseRAW;
    @Column(name = "non_gpscause")
    private String nonGPSCause;
    @Column(name = "non_gpscause_raw")
    private String nonGPSCauseRAW;
    @Column(name = "is_quick_spin")
    private String isQuickSpin;
    private String battery;
    @Column(name = "s_wave_height")
    private String sWaveHeight;
    @Column(name = "fly_time")
    private String flyTime;
    @Column(name = "motor_revolution")
    private String motorRevolution;
    @Column(name = "flyc_version")
    private String flycVersion;
    @Column(name = "drone_type")
    private String droneType;
    @Column(name = "imu_init_fail_reason")
    private String imuInitFailReason;
    @Column(name = "imu_init_fail_reason_raw")
    private String imuInitFailReasonRAW;
    @Column(name = "motor_fail_reason")
    private String motorFailReason;
    @Column(name = "motor_fail_reason_raw")
    private String motorFailReasonRAW;
    @Column(name = "ctrl_device")
    private String ctrlDevice;
    @Column(name = "ctrl_device_raw")
    private String ctrlDeviceRAW;

    public OSDColumn(){

    }

    public OSDColumn(String timestamp, String latitude, String longitude, String height, String altitude, String xSpeed,
                     String ySpeed, String zSpeed, String pitch, String roll, String yaw, String flycState,
                     String flycStateRAW, String flycCommand, String flycCommandRAW, String canIOCWork,
                     String groundOrSky, String isMotorUp, String isSwaveWork, String goHomeStatus,
                     String goHomeStatusRAW, String isImuPreheated, String isVisionUsed, String voltageWarning,
                     String modeChannel, String isGPSused, String compassError, String waveError,
                     String gpsLevel, String batteryType, String isAcceletorOverRange, String isVibrating,
                     String isBarometerDeadInAir, String isMotorBlocked, String isNotEnoughForce,
                     String isPropellerCatapult, String isGoHomeHeightModified, String isOutOfLimit,
                     String gpsNum, String flightAction, String flightActionRAW, String motorStartFailedCause,
                     String motorStartFailedCauseRAW, String nonGPSCause, String nonGPSCauseRAW, String isQuickSpin,
                     String battery, String sWaveHeight, String flyTime, String motorRevolution, String flycVersion,
                     String droneType, String imuInitFailReason, String imuInitFailReasonRAW, String motorFailReason,
                     String motorFailReasonRAW, String ctrlDevice, String ctrlDeviceRAW) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
        this.altitude = altitude;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.zSpeed = zSpeed;
        this.pitch = pitch;
        this.roll = roll;
        this.yaw = yaw;
        this.flycState = flycState;
        this.flycStateRAW = flycStateRAW;
        this.flycCommand = flycCommand;
        this.flycCommandRAW = flycCommandRAW;
        this.canIOCWork = canIOCWork;
        this.groundOrSky = groundOrSky;
        this.isMotorUp = isMotorUp;
        this.isSwaveWork = isSwaveWork;
        this.goHomeStatus = goHomeStatus;
        this.goHomeStatusRAW = goHomeStatusRAW;
        this.isImuPreheated = isImuPreheated;
        this.isVisionUsed = isVisionUsed;
        this.voltageWarning = voltageWarning;
        this.modeChannel = modeChannel;
        this.isGPSused = isGPSused;
        this.compassError = compassError;
        this.waveError = waveError;
        this.gpsLevel = gpsLevel;
        this.batteryType = batteryType;
        this.isAcceletorOverRange = isAcceletorOverRange;
        this.isVibrating = isVibrating;
        this.isBarometerDeadInAir = isBarometerDeadInAir;
        this.isMotorBlocked = isMotorBlocked;
        this.isNotEnoughForce = isNotEnoughForce;
        this.isPropellerCatapult = isPropellerCatapult;
        this.isGoHomeHeightModified = isGoHomeHeightModified;
        this.isOutOfLimit = isOutOfLimit;
        this.gpsNum = gpsNum;
        this.flightAction = flightAction;
        this.flightActionRAW = flightActionRAW;
        this.motorStartFailedCause = motorStartFailedCause;
        this.motorStartFailedCauseRAW = motorStartFailedCauseRAW;
        this.nonGPSCause = nonGPSCause;
        this.nonGPSCauseRAW = nonGPSCauseRAW;
        this.isQuickSpin = isQuickSpin;
        this.battery = battery;
        this.sWaveHeight = sWaveHeight;
        this.flyTime = flyTime;
        this.motorRevolution = motorRevolution;
        this.flycVersion = flycVersion;
        this.droneType = droneType;
        this.imuInitFailReason = imuInitFailReason;
        this.imuInitFailReasonRAW = imuInitFailReasonRAW;
        this.motorFailReason = motorFailReason;
        this.motorFailReasonRAW = motorFailReasonRAW;
        this.ctrlDevice = ctrlDevice;
        this.ctrlDeviceRAW = ctrlDeviceRAW;
    }
}
