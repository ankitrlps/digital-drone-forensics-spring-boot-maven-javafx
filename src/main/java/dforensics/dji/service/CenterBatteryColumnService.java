package dforensics.dji.service;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.entity.CenterBatteryColumn;
import org.springframework.stereotype.Component;

@Component
public interface CenterBatteryColumnService extends GenericService<CenterBatteryColumn> {
    void save(CenterBatteryColumn centerBatteryColumn);
    SingleColumn getProductDate();
}
