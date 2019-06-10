package dforensics.dji.service.impl;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.OSDColumn;
import dforensics.dji.repository.OSDColumnRepo;
import dforensics.dji.service.OSDColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OSDServiceImpl implements OSDColumnService {

    @Autowired
    OSDColumnRepo osdColumnRepo;

    @Override
    public void save(List<OSDColumn> entity) {
        osdColumnRepo.saveAll(entity);
    }

    @Override
    public void delete() {
        osdColumnRepo.deleteAll();
    }

    @Override
    public List<TimeAndColumn> getAltitudeWithTime() {

        List<TimeAndColumn> osdColumns = osdColumnRepo.fetchTimeAndAltitude()
                .parallelStream().filter(timeAndColumn -> timeAndColumn.getId()%2 == 0).collect(Collectors.toList());
/*        Iterator<OSDColumn> iterable = osdColumnRepo.findAll().iterator();
        List<TimeAndColumn> list = new ArrayList<>();
        //int j = 1;
        while(iterable.hasNext()){
            //j++;
            OSDColumn osd = iterable.next();
            list.add(new TimeAndColumn(osd.getTimestamp(), osd.getAltitude()));

*//*            if(j >= count - 10 && j <= count) {
                list.add(new TimeAndColumn(osd.getTimestamp(), osd.getAltitude()));
            }
            if(count - 10 > j && j%2 == 0) {
                list.add(new TimeAndColumn(osd.getTimestamp(), osd.getAltitude()));
            }
            if(j > count) break;*//*
        }
        System.out.println(list.size());*/
        return osdColumns;
    }

    @Override
    public List<TimeAndThreeColumns> getPitchRollYawWithTime() {
        List<TimeAndThreeColumns> threeColumns = osdColumnRepo.fetchTimeAndPitchRollYaw().parallelStream()
                .filter(columns -> columns.getId()%2 == 0).collect(Collectors.toList());
        log.info("Pitch Roll Yaw are fetched from DB");
        return threeColumns;
    }

    @Override
    public List<TimeAndColumn> getFlyCStateWithTime() {
        List<TimeAndColumn> flyCStateList = osdColumnRepo.fetchTimeAndFlyState().parallelStream()
                    .filter(timeAndFlyState -> timeAndFlyState.getId()%2 == 0).collect(Collectors.toList());
        log.info("Fly State is fetched from DB");
        return flyCStateList;
    }

    @Override
    public List<TimeAndColumn> getSatellitesWithTime() {
        List<TimeAndColumn> noOfSatellites = osdColumnRepo.fetchTimeAndSatellites().parallelStream()
                .filter(satellites -> satellites.getId()%2 ==0).collect(Collectors.toList());
        log.info("Satellites is fetched from DB");
        log.info("Satel Size: " + noOfSatellites.size());
        return noOfSatellites;
    }

    @Override
    public List<OSDColumn> getAllDetails() {
        return osdColumnRepo.fetchAllDetails();
    }

}
