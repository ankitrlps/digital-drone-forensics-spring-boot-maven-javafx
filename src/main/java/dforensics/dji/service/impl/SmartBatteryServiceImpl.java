package dforensics.dji.service.impl;

import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.domain.TimeAndTwoColumn;
import dforensics.dji.entity.SmartBatteryColumn;
import dforensics.dji.repository.SmartBatteryRepo;
import dforensics.dji.service.SmartBatteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SmartBatteryServiceImpl implements SmartBatteryService {

    @Autowired
    SmartBatteryRepo smartBatteryRepo;

    @Override
    public void save(List<SmartBatteryColumn> entity) {
        smartBatteryRepo.saveAll(entity);
    }

    @Override
    public void delete() {
        smartBatteryRepo.deleteAll();
    }

    @Override
    public List<TimeAndColumn> getBatteryWithTime() {
        List<TimeAndColumn> smartBattery = smartBatteryRepo.fetchTimeAndBattery()
                .parallelStream().filter(battery -> battery.getId()%2 == 0).collect(Collectors.toList());
        log.info("Battery is fetched from DB");
        return smartBattery;
    }

    @Override
    public List<TimeAndTwoColumn> getBatteryAndVoltage() {
        List<TimeAndTwoColumn> timeAndTwoColumns = new ArrayList<>();
        List<TimeAndTwoColumn> detailsFromDb = smartBatteryRepo.fetchBatteryAndVoltage();
        int size = detailsFromDb.size();
        timeAndTwoColumns.add(detailsFromDb.get(10));
        timeAndTwoColumns.add(detailsFromDb.get(size - 1));
        return timeAndTwoColumns;
    }
}
