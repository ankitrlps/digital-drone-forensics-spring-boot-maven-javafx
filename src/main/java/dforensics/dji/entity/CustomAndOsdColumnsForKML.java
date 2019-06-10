package dforensics.dji.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomAndOsdColumnsForKML {

    private String timestamp;
    private String isPhoto;
    private String isVideo;
    private String latitude;
    private String longitude;
    private String altitude;

    public CustomAndOsdColumnsForKML(){}

    public CustomAndOsdColumnsForKML(String timestamp, String isPhoto, String isVideo, String latitude, String longitude, String altitude) {
        this.timestamp = timestamp;
        this.isPhoto = isPhoto;
        this.isVideo = isVideo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
}
