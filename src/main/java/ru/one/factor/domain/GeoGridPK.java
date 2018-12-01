package ru.one.factor.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class GeoGridPK implements Serializable {

    @Column(name = "tile_x")
    private int tileX;

    @Column(name = "tile_y")
    private int tileY;

    public GeoGridPK(double lon, double lat) {
        this.tileX = (int) lon;
        this.tileY = (int) lat;
    }
}
