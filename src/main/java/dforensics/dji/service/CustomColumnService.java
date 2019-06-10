package dforensics.dji.service;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.CustomColumn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomColumnService extends GenericService<CustomColumn> {

    List<TimeAndColumn> getHSpeedWithTime();

    SingleColumn getDistanceTravelled();
}
