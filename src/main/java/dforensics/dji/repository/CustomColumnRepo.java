package dforensics.dji.repository;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.CustomColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomColumnRepo extends CrudRepository<CustomColumn, Long> {

    @Query(nativeQuery = true)
    List<TimeAndColumn> fetchTimeAndHSpeed();

    @Query(nativeQuery = true)
    List<SingleColumn> fetchDistanceTravelled();
}
