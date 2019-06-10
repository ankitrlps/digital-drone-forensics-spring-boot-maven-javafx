package dforensics.dji.service.impl;

import dforensics.dji.entity.DetailsColumn;
import dforensics.dji.repository.DetailsColumnRepo;
import dforensics.dji.service.DetailsColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetailsServiceImpl implements DetailsColumnService {

    @Autowired
    DetailsColumnRepo detailsColumnRepo;

    @Override
    public void save(List<DetailsColumn> entity) {
        detailsColumnRepo.saveAll(entity);
    }

    @Override
    public void delete() {
        detailsColumnRepo.deleteAll();
    }

    @Override
    public void save(DetailsColumn detailsColumn) {
        detailsColumnRepo.save(detailsColumn);
    }

    @Override
    public DetailsColumn getDetails() {
        List<DetailsColumn> detailsColumns = detailsColumnRepo.fetchDetails();
        int size = detailsColumns.size();
        return detailsColumns.get(size - 1);
    }
}
