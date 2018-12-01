package ru.one.factor.service;

import static ru.one.factor.exception.Messages.USER_POINT_NOT_FOUND;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.one.factor.domain.GeoGrid;
import ru.one.factor.domain.GeoGridPK;
import ru.one.factor.domain.UserPoint;
import ru.one.factor.exception.NotFoundException;
import ru.one.factor.repository.IUserPointRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserPointServiceImpl implements IUserPointService {

    private final IUserPointRepository userPointRepository;
    private final IGeoGridService geoGridService;

    public UserPointServiceImpl(IUserPointRepository userPointRepository,
            IGeoGridService geoGridService) {
        this.userPointRepository = userPointRepository;
        this.geoGridService = geoGridService;
    }

    @Override
    public UserPoint createUserPoint(UserPoint userPoint) {
        return userPointRepository.save(userPoint);
    }

    @Override
    @Transactional(readOnly = true)
    public UserPoint getUserPoint(int userPointId) {
        return userPointRepository.findById(userPointId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_POINT_NOT_FOUND, userPointId)));
    }

    @Override
    public UserPoint modifyUserPoint(UserPoint userPoint) {
        return userPointRepository.save(userPoint);
    }

    @Override
    public void deleteUserPoint(int userPointId) {
        userPointRepository.deleteById(userPointId);
    }

    @Override
    public boolean closeness(int userPointId, double lat, double lon) {
        UserPoint userPoint = getUserPoint(userPointId);
        GeoGrid geoGrid = geoGridService.getGeoGrid(new GeoGridPK(lon, lat));

        Point upPoint = getPointFromLatLon(userPoint.getLat(), userPoint.getLon());
        Point geoPoint = getPointFromLatLon(lat, lon);
        // in meters
        double distance = EarthCalc.harvesineDistance(upPoint, geoPoint);
        return distance <= geoGrid.getDistanceError();
    }

    @Override
    public long countUserPoints(int tileX, int tileY) {
        GeoGrid geoGrid = geoGridService.getGeoGrid(new GeoGridPK(tileX, tileY));
        double latBegin = (double) geoGrid.getId().getTileY();
        double lonBegin = (double) geoGrid.getId().getTileX();
        return userPointRepository.countByLatBetweenAndLonBetween(latBegin, latBegin + 1, lonBegin, lonBegin + 1);
    }

    private Point getPointFromLatLon(double lat, double lon) {
        Coordinate latCoord = Coordinate.fromDegrees(lat);
        Coordinate lonCoord = Coordinate.fromDegrees(lon);
        return Point.at(latCoord, lonCoord);
    }
}
