package ru.one.factor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.one.factor.domain.GeoGrid;
import ru.one.factor.domain.GeoGridPK;

public interface IGeoGridRepository extends JpaRepository<GeoGrid, GeoGridPK> {

}
