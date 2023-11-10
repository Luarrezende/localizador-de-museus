package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.stereotype.Service;

/**
 * Exception to invalid coordinate.
 */
@Service
public abstract class MuseumService implements MuseumServiceInterface {
  MuseumFakeDatabase museumFakeDatabase;

  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  @Override
  public Museum createMuseum(Museum museum) {
    double latitude = museum.getCoordinate().latitude();
    double longitude = museum.getCoordinate().longitude();

    Coordinate coordinate = new Coordinate(latitude, longitude);

    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    return museumFakeDatabase.saveMuseum(museum);
  }
}
