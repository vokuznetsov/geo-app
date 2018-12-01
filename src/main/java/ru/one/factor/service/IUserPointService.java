package ru.one.factor.service;

import ru.one.factor.domain.UserPoint;

public interface IUserPointService {

    UserPoint createUserPoint(UserPoint userPoint);

    UserPoint getUserPoint(int userPointId);

    UserPoint modifyUserPoint(UserPoint userPoint);

    void deleteUserPoint(int userPointId);

    boolean closeness(int userPointId, double lat, double lon);

    long countUserPoints(int tileX, int tileY);

}
