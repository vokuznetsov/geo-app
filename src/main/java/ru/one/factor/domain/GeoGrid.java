package ru.one.factor.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "geo_grid")
public class GeoGrid {

    @EmbeddedId
    private GeoGridPK id;

    @Column(name = "distance_error")
    private double distanceError;

}
