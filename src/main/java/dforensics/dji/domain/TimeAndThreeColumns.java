package dforensics.dji.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TimeAndThreeColumns {
    @Id
    int id;
    String timestamp;
    String column1;
    String column2;
    String column3;
}
