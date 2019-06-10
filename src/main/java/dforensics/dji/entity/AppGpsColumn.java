package dforensics.dji.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery(
        name = "AppGpsColumn.fetchAppGpdDetails",
        query = "SELECT * from app_gps_column",
        resultClass = AppGpsColumn.class
)

@Data
@Entity
public class AppGpsColumn {

    @Id
    private int id;
    private String timestamp;
    @Column(name = "ag_lat")
    private String agLat;
    @Column(name = "ag_long")
    private String agLong;
    @Column(name = "ag_acc")
    private String agAcc;

    public AppGpsColumn(){}

    public AppGpsColumn(String timestamp, String agLat, String agLong, String agAcc) {
        this.timestamp = timestamp;
        this.agLat = agLat;
        this.agLong = agLong;
        this.agAcc = agAcc;
    }
}
