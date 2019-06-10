package dforensics.dji.repository;

import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.entity.AppGpsColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppGpsColumnRepo extends CrudRepository<AppGpsColumn, Long> {
    @Query(nativeQuery = true)
    List<AppGpsColumn> fetchAppGpdDetails();
}
