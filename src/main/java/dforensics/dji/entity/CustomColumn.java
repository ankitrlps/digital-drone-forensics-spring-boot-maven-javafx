package dforensics.dji.entity;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndColumn;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(
        name = "CustomColumn.fetchTimeAndHSpeed",
        query = "SELECT id, update_Time AS timestamp, h_Speed AS column from custom_column",
        resultClass = TimeAndColumn.class
)

@NamedNativeQuery(
        name = "CustomColumn.fetchDistanceTravelled",
        query = "select id, distance_Travelled AS column from custom_column",
        resultClass = SingleColumn.class
)

@Data
@Entity
public class CustomColumn {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String updateTime;
    String isPhoto;
    String isVideo;
    String hSpeed;
    String distance;
    String distanceTravelled;
    String hSpeedRunning_max;
    String distanceRunning_max;
    String heightRunning_max;

    public CustomColumn(){}

    public CustomColumn(String updateTime, String isPhoto, String isVideo, String hSpeed, String distance, String distanceTravelled, String hSpeedRunning_max, String distanceRunning_max, String heightRunning_max) {
        this.updateTime = updateTime;
        this.isPhoto = isPhoto;
        this.isVideo = isVideo;
        this.hSpeed = hSpeed;
        this.distance = distance;
        this.distanceTravelled = distanceTravelled;
        this.hSpeedRunning_max = hSpeedRunning_max;
        this.distanceRunning_max = distanceRunning_max;
        this.heightRunning_max = heightRunning_max;
    }
}
