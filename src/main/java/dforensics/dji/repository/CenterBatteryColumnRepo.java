package dforensics.dji.repository;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.entity.CenterBatteryColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterBatteryColumnRepo extends CrudRepository<CenterBatteryColumn, Long> {

    @Query(nativeQuery = true)
    List<SingleColumn> fetchProductDate();
}
