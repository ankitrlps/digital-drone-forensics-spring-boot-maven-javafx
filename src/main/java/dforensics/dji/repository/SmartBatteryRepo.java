package dforensics.dji.repository;

import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.domain.TimeAndTwoColumn;
import dforensics.dji.entity.SmartBatteryColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartBatteryRepo extends CrudRepository<SmartBatteryColumn, Long> {

    @Query(nativeQuery = true)
    List<TimeAndColumn> fetchTimeAndBattery();

    List<TimeAndTwoColumn> fetchBatteryAndVoltage();
}
