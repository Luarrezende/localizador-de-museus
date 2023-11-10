package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Museum controller class.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  MuseumServiceInterface museumServiceInterface;

  public MuseumController(MuseumServiceInterface museumServiceInterface) {
    this.museumServiceInterface = museumServiceInterface;
  }

  /**
   * Route to create a new Museum.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumDto museumDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumDto);
    this.museumServiceInterface.createMuseum(museum);

    return  ResponseEntity.status(HttpStatus.CREATED).body(museum);
  }

  /**
   * Route to get.
   */
  @GetMapping("/closest")
  public ResponseEntity<Museum> closestMuseum(
      @RequestParam Double lat, Double lng,
      @RequestParam("max_dist_km") Double maxDistanceKm
  ) {
    Coordinate coordinate = new Coordinate(lat, lng);
    Museum closestM = this.museumServiceInterface.getClosestMuseum(coordinate, maxDistanceKm);
    return ResponseEntity.status(HttpStatus.OK).body(closestM);
  }
}
