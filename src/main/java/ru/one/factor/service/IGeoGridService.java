package ru.one.factor.service;

import ru.one.factor.domain.GeoGrid;
import ru.one.factor.domain.GeoGridPK;

public interface IGeoGridService {

    GeoGrid createGeoGrid(GeoGrid geoGrid);

    GeoGrid getGeoGrid(GeoGridPK pk);

    GeoGrid modifyGeoGrid(GeoGrid geoGrid);

    void deleteGeoGrid(GeoGridPK pk);

}
