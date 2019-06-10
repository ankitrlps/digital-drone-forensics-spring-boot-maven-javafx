package dforensics.dji.repository;

import dforensics.dji.domain.TimeAndTwoColumn;
import dforensics.dji.entity.HomeColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeColumnRepo extends CrudRepository<HomeColumn, Long> {

    @Query(nativeQuery = true)
    TimeAndTwoColumn fetchLatAndLong();
}
