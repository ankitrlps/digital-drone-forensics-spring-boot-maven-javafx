package dforensics.dji.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TimeAndColumn {

    @Id
    private int id;
    private String timestamp;
    private String column;

}
