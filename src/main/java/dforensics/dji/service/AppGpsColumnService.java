package dforensics.dji.service;

import dforensics.dji.entity.AppGpsColumn;
import org.springframework.stereotype.Component;

@Component
public interface AppGpsColumnService extends GenericService<AppGpsColumn> {
    void save(AppGpsColumn appGpsColumn);
    AppGpsColumn getAppGpsDetails();
}
