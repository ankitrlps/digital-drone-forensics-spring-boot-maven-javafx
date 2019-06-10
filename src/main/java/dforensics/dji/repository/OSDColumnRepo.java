package dforensics.dji.repository;

import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.OSDColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OSDColumnRepo extends CrudRepository<OSDColumn, Long> {

    @Query(nativeQuery = true)
    List<TimeAndColumn> fetchTimeAndAltitude();

    @Query(nativeQuery = true)
    List<TimeAndThreeColumns> fetchTimeAndPitchRollYaw();

    @Query(nativeQuery = true)
    List<TimeAndColumn> fetchTimeAndFlyState();

    @Query(nativeQuery = true)
    List<TimeAndColumn> fetchTimeAndSatellites();

    @Query(nativeQuery = true)
    List<OSDColumn> fetchAllDetails();
}
