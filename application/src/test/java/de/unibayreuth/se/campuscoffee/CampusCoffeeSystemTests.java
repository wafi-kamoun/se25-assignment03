package de.unibayreuth.se.campuscoffee;

import de.unibayreuth.se.campuscoffee.api.dtos.PosDto;
import de.unibayreuth.se.campuscoffee.api.mapper.PosDtoMapper;
import de.unibayreuth.se.campuscoffee.domain.Pos;
import de.unibayreuth.se.campuscoffee.domain.TestFixtures;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class CampusCoffeeSystemTests extends AbstractSystemTest {

    @Autowired
    private PosDtoMapper posDtoMapper;

    @Test
    void getAllCreatedPos() {
        List<Pos> createdPosList = TestFixtures.createPos(posService);

        List<Pos> retrievedPos = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/pos")
                .then()
                .statusCode(200)
                .body(".", hasSize(createdPosList.size()))
                .and()
                .extract().jsonPath().getList("$", PosDto.class)
                .stream()
                .map(posDtoMapper::toDomain)
                .toList();

        assertThat(retrievedPos)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("createdAt", "updatedAt") // prevent issues due to differing timestamps after conversions
                .containsExactlyInAnyOrderElementsOf(createdPosList);
    }

    @Test
    void getPosById() {
        List<Pos> createdPosList = TestFixtures.createPos(posService);
        Pos createdPos = createdPosList.getFirst();

        Pos retrievedPos = posDtoMapper.toDomain(
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/api/pos/{id}", createdPos.getId())
                        .then()
                        .statusCode(200)
                        .extract().as(PosDto.class)
        );

        assertThat(retrievedPos)
                .usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt") // prevent issues due to differing timestamps after conversions
                .isEqualTo(createdPos);
    }

}