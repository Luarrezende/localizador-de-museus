package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * This record represents a data transfer object (DTO) for creating a new museum entry.
 * It encapsulates essential information about a museum, including its name, description,
 * address, collection type, subject, URL, and geographic coordinates.
*/
public record MuseumCreationDto(
    String name, String description, String address,
    String collectionType, String subject, String url,
    Coordinate coordinate
) {
}
