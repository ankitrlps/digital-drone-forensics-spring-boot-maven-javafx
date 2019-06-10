package dforensics.dji.service;

import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.entity.GimbalColumn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GimbalColumnService extends GenericService<GimbalColumn> {

    List<TimeAndThreeColumns> getGimbalPRYWithTime();
}
