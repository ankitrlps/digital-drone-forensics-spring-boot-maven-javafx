package dforensics.dji.service;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.OSDColumn;
import java.util.List;

public interface OSDColumnService extends GenericService<OSDColumn> {

    List<TimeAndColumn> getAltitudeWithTime();

    List<TimeAndThreeColumns> getPitchRollYawWithTime();

    List<TimeAndColumn> getFlyCStateWithTime();

    List<TimeAndColumn> getSatellitesWithTime();

    List<OSDColumn> getAllDetails();
}
