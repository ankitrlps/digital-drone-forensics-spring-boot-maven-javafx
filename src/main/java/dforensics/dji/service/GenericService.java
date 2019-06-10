package dforensics.dji.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenericService <T> {
    void save(List<T> entity);
    void delete();
}
