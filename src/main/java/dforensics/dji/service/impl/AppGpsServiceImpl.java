package dforensics.dji.service.impl;

import dforensics.dji.entity.AppGpsColumn;
import dforensics.dji.repository.AppGpsColumnRepo;
import dforensics.dji.service.AppGpsColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppGpsServiceImpl implements AppGpsColumnService {

    @Autowired
    AppGpsColumnRepo appGpsColumnRepo;

    @Override
    public void save(AppGpsColumn appGpsColumn) {
        appGpsColumnRepo.save(appGpsColumn);
    }

    @Override
    public AppGpsColumn getAppGpsDetails() {
        List<AppGpsColumn> appGpsColumns = appGpsColumnRepo.fetchAppGpdDetails();
        int size = appGpsColumns.size();
        return appGpsColumns.get(size - 1);
    }

    @Override
    public void save(List<AppGpsColumn> entity) {
        appGpsColumnRepo.saveAll(entity);
    }

    @Override
    public void delete() {
        appGpsColumnRepo.deleteAll();
    }
}
