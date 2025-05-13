package de.unibayreuth.se.campuscoffee.api.dtos;

import de.unibayreuth.se.campuscoffee.domain.CampusType;
import de.unibayreuth.se.campuscoffee.domain.PosType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for POS metadata.
 *
 */
@Data
public class PosDto {
        @Nullable
        private final Long id; // POS id is null when creating or update a new task
        @Nullable
        private final LocalDateTime createdAt; // is null when using DTO to create or update a new POS
        @Nullable
        private final LocalDateTime updatedAt; // is null when using DTO to create or update a new POS
        @NotBlank
        @Size(max = 255, message = "Name can be at most 255 characters long.")
        private final String name;
        @NotNull
        private final String description;
        @NotNull
        private PosType type;
        @NotNull
        private CampusType campus;
        @NotBlank
        private String street;
        @NotBlank
        private String houseNumber;
        @NotNull
        private Integer postalCode;
        @NotBlank
        private String city;
}
