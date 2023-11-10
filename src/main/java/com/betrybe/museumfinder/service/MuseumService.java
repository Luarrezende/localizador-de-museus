package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Exception to invalid coordinate.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  MuseumFakeDatabase museumFakeDatabase;

  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  private Boolean checkCoordinate(Coordinate coordinate) {
    return CoordinateUtil.isCoordinateValid(coordinate);
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    Optional<Museum> closeM = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);
    if (!checkCoordinate(coordinate)) {
      throw new InvalidCoordinateException();
    }

    if (closeM.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return closeM.get();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (!checkCoordinate(museum.getCoordinate())) {
      throw new InvalidCoordinateException();
    }

    return museumFakeDatabase.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    Optional<Museum> museum = museumFakeDatabase.getMuseum(id);
    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    }
    return museum.get();
  }
}
