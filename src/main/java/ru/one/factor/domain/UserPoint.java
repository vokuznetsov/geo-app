package ru.one.factor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_point")
public class UserPoint {

    @Id
    @SequenceGenerator(name = "user_point_generator", sequenceName = "user_point_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_point_generator")
    @Column(name = "user_id", nullable = false, unique = true)
    private int id;

    private double lat;

    private double lon;

    public UserPoint(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }
}
