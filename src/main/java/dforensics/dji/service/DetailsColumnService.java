package dforensics.dji.service;

import dforensics.dji.entity.DetailsColumn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DetailsColumnService extends GenericService<DetailsColumn> {

    void save(DetailsColumn detailsColumn);
    DetailsColumn getDetails();
}
