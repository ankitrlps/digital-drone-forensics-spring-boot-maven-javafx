package dforensics.dji.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TimeAndTwoColumn {

    @Id
    private int id;
    private String timestamp;
    private String column1;
    private String column2;
}
