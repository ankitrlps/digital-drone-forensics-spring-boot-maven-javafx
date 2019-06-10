package dforensics.dji.service;

import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.domain.TimeAndTwoColumn;
import dforensics.dji.entity.SmartBatteryColumn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SmartBatteryService extends GenericService<SmartBatteryColumn> {

    List<TimeAndColumn> getBatteryWithTime();

    List<TimeAndTwoColumn> getBatteryAndVoltage();
}
