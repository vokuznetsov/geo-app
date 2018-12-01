package ru.one.factor.service;

import static ru.one.factor.exception.Messages.GEO_GRID_NOT_FOUND;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.one.factor.domain.GeoGrid;
import ru.one.factor.domain.GeoGridPK;
import ru.one.factor.exception.NotFoundException;
import ru.one.factor.repository.IGeoGridRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class GeoGridServiceImpl implements IGeoGridService {

    private final IGeoGridRepository geoGridRepository;

    public GeoGridServiceImpl(IGeoGridRepository geoGridRepository) {
        this.geoGridRepository = geoGridRepository;
    }

    @Override
    public GeoGrid createGeoGrid(GeoGrid geoGrid) {
        return geoGridRepository.save(geoGrid);
    }

    @Override
    @Transactional(readOnly = true)
    public GeoGrid getGeoGrid(GeoGridPK pk) {
        return geoGridRepository.findById(pk)
                .orElseThrow(() ->
                        new NotFoundException(String.format(GEO_GRID_NOT_FOUND, pk.getTileX(), pk.getTileY())));
    }

    @Override
    public GeoGrid modifyGeoGrid(GeoGrid geoGrid) {
        return geoGridRepository.save(geoGrid);
    }

    @Override
    public void deleteGeoGrid(GeoGridPK pk) {
        geoGridRepository.deleteById(pk);
    }
}
