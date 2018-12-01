package ru.one.factor.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.one.factor.domain.UserPoint;
import ru.one.factor.service.IUserPointService;

@RestController
@RequestMapping("/api/userPoint")
@Api(tags = "user-point", description = "Operations with user points", protocols = "http, https",
        consumes = "application/json", produces = "application/json")
public class UserPointController {

    private final IUserPointService userPointService;

    public UserPointController(IUserPointService userPointService) {
        this.userPointService = userPointService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new user point", response = UserPoint.class)
    public UserPoint createUserPoint(@RequestParam(value = "lat") double lat,
            @RequestParam(value = "lon") double lon) {
        return userPointService.createUserPoint(new UserPoint(lat, lon));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user point", response = UserPoint.class)
    public UserPoint getUserPoint(@PathVariable("id") int id) {
        return userPointService.getUserPoint(id);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modify user point", response = UserPoint.class)
    public UserPoint modifyUserPoint(@RequestBody @Valid UserPoint userPoint) {
        return userPointService.modifyUserPoint(userPoint);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete user point")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUserPoint(@PathVariable("id") int id) {
        userPointService.deleteUserPoint(id);
    }

    @GetMapping(value = "/{id}/closeness", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Check closeness of user point to coordinates", response = Boolean.class)
    public boolean closeness(@PathVariable("id") int userPointId, @RequestParam("lat") double lat,
            @RequestParam("lon") double lon) {
        return userPointService.closeness(userPointId, lat, lon);
    }

    @GetMapping(value = "/count", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Count user points inside geographic grid with coordinates", response = Long.class)
    public long countUserPoints(@RequestParam("tile_x") int tileX, @RequestParam("tile_y") int tileY) {
        return userPointService.countUserPoints(tileX, tileY);
    }
}
