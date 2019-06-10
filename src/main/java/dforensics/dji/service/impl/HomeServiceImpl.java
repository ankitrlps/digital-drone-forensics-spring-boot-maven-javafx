package dforensics.dji.service.impl;

import dforensics.dji.domain.TimeAndTwoColumn;
import dforensics.dji.entity.HomeColumn;
import dforensics.dji.repository.HomeColumnRepo;
import dforensics.dji.service.HomeColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeServiceImpl implements HomeColumnService {

    @Autowired
    HomeColumnRepo homeColumnRepo;

    @Override
    public void save(HomeColumn homeColumn) {
        homeColumnRepo.save(homeColumn);
    }

    @Override
    public TimeAndTwoColumn fetchLatAndLongHomeDetails() {
        return homeColumnRepo.fetchLatAndLong();
    }

    @Override
    public void save(List<HomeColumn> entity) {
        homeColumnRepo.saveAll(entity);
    }

    @Override
    public void delete() {
    homeColumnRepo.deleteAll();
    }
}
