package dforensics.dji.service.impl;

import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.entity.GimbalColumn;
import dforensics.dji.repository.GimbalColumnRepo;
import dforensics.dji.service.GimbalColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GimbalServiceImpl implements GimbalColumnService {

    @Autowired
    GimbalColumnRepo gimbalColumnRepo;

    @Override
    public void save(List<GimbalColumn> entity) {
        gimbalColumnRepo.saveAll(entity);
    }

    @Override
    public void delete() {
        gimbalColumnRepo.deleteAll();
    }

    @Override
    public List<TimeAndThreeColumns> getGimbalPRYWithTime() {
        List<TimeAndThreeColumns> gPRY = gimbalColumnRepo.fetchTimeAndGimbalPRY()
                .parallelStream().filter(gRpy -> gRpy.getId()%2 == 0).collect(Collectors.toList());
        log.info("Gimbal Pitch Roll Yaw are fetched from DB");
        return gPRY;
    }
}
