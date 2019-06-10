package dforensics.dji.service;

import dforensics.dji.domain.TimeAndTwoColumn;
import dforensics.dji.entity.HomeColumn;
import org.springframework.stereotype.Component;

@Component
public interface HomeColumnService extends GenericService<HomeColumn> {

    void save(HomeColumn homeColumn);
    TimeAndTwoColumn fetchLatAndLongHomeDetails();
}
