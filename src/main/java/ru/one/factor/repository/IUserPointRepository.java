package ru.one.factor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.one.factor.domain.UserPoint;

public interface IUserPointRepository extends JpaRepository<UserPoint, Integer> {

    long countByLatBetweenAndLonBetween(double latBegin, double latEnd, double lonBegin, double lonEnd);

}
