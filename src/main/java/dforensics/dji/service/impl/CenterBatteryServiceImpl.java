package dforensics.dji.service.impl;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.CenterBatteryColumn;
import dforensics.dji.repository.CenterBatteryColumnRepo;
import dforensics.dji.service.CenterBatteryColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterBatteryServiceImpl implements CenterBatteryColumnService {

    @Autowired
    CenterBatteryColumnRepo centerBatteryColumnRepo;


    @Override
    public void save(CenterBatteryColumn centerBatteryColumn) {
        centerBatteryColumnRepo.save(centerBatteryColumn);
    }

    @Override
    public SingleColumn getProductDate() {
        List<SingleColumn> singleColumns = centerBatteryColumnRepo.fetchProductDate();
        int size = singleColumns.size();
        return singleColumns.get(size - 1);
    }

    @Override
    public void save(List<CenterBatteryColumn> entity) {
        centerBatteryColumnRepo.saveAll(entity);
    }

    @Override
    public void delete() {
        centerBatteryColumnRepo.deleteAll();
    }
}
