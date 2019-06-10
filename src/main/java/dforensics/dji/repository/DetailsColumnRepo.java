package dforensics.dji.repository;

import dforensics.dji.entity.DetailsColumn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsColumnRepo extends CrudRepository<DetailsColumn, Long> {

    @Query(nativeQuery = true)
    List<DetailsColumn> fetchDetails();
}
