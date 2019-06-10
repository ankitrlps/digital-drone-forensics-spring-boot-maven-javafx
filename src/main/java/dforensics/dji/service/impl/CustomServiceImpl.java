package dforensics.dji.service.impl;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.CustomColumn;
import dforensics.dji.repository.CustomColumnRepo;
import dforensics.dji.service.CustomColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomServiceImpl implements CustomColumnService {

    @Autowired
    CustomColumnRepo customColumnRepo;

    @Override
    public void save(List<CustomColumn> entity) {
        customColumnRepo.saveAll(entity);
    }

    @Override
    public void delete() {
        customColumnRepo.deleteAll();
    }

    @Override
    public List<TimeAndColumn> getHSpeedWithTime() {
        List<TimeAndColumn> speedList = customColumnRepo.fetchTimeAndHSpeed()
                .parallelStream().filter(speed -> speed.getId()%2 == 0).collect(Collectors.toList());
        log.info("Speed is fetched from DB");
        return speedList;
    }

    @Override
    public SingleColumn getDistanceTravelled() {
        List<SingleColumn> distanceTravelled = customColumnRepo.fetchDistanceTravelled();
        int size = distanceTravelled.size();
        return distanceTravelled.get(size - 1);
    }
}
