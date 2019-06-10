package dforensics.dji.repository;

import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.entity.GimbalColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GimbalColumnRepo extends CrudRepository<GimbalColumn, Long> {

    @Query(nativeQuery = true)
    List<TimeAndThreeColumns> fetchTimeAndGimbalPRY();
}
