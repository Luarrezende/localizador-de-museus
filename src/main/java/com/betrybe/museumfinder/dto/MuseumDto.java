package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * This record represents a Data Transfer Object (DTO) for a museum entity.
 * It encapsulates information about a museum, including its unique identifier (ID),
 * name, description, address, collection type, subject, URL, and geographic coordinates.
*/
public record MuseumDto(
    Long id, String name, String description, String address,
    String collectionType, String subject, String url,
    Coordinate coordinate
) {
}
