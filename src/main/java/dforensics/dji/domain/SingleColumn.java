package dforensics.dji.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SingleColumn {

    @Id
    private int id;
    private String column;

    public SingleColumn(){}

    public SingleColumn(String column) {
        this.column = column;
    }
}
