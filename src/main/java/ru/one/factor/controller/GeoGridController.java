package ru.one.factor.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.one.factor.domain.GeoGrid;
import ru.one.factor.domain.GeoGridPK;
import ru.one.factor.service.IGeoGridService;

@RestController
@RequestMapping("/api/geoGrid")
@Api(tags = "geo-grid", description = "Operations with geographic grid", protocols = "http, https",
        consumes = "application/json", produces = "application/json")
public class GeoGridController {

    private final IGeoGridService geoGridService;

    public GeoGridController(IGeoGridService geoGridService) {
        this.geoGridService = geoGridService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new geo grid", response = GeoGrid.class)
    public GeoGrid createUserPoint(@RequestBody @Valid GeoGrid geoGrid) {
        return geoGridService.createGeoGrid(geoGrid);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get geo grid by tile_x and tile_y", response = GeoGrid.class)
    public GeoGrid getUserPoint(@RequestParam("tileX") int tileX, @RequestParam("tileY") int tileY) {
        return geoGridService.getGeoGrid(new GeoGridPK(tileX, tileY));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modify geo grid", response = GeoGrid.class)
    public GeoGrid modifyUserPoint(@RequestBody @Valid GeoGrid geoGrid) {
        return geoGridService.modifyGeoGrid(geoGrid);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete geo grid")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUserPoint(@RequestParam("tileX") int tileX, @RequestParam("tileY") int tileY) {
        geoGridService.deleteGeoGrid(new GeoGridPK(tileX, tileY));
    }

}
