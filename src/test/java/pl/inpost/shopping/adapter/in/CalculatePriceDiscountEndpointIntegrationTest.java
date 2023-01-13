package pl.inpost.shopping.adapter.in;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import pl.inpost.shopping.BaseIntegrationTest;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static org.hamcrest.Matchers.is;
import static pl.inpost.shopping.testutils.TestConstants.*;

class CalculatePriceDiscountEndpointIntegrationTest extends BaseIntegrationTest {

    @ParameterizedTest
    @MethodSource
    public void shouldCalculateDiscountAndRespondWithCorrectJson(UUID productId, Integer amount, BigDecimal priceBase, BigDecimal priceDiscounted) {
        // @formatter:off
        given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                .param("productId", productId)
                .param("amount", amount)
        .when()
                .get(url("/calculate-price-discount"))
        .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value())
                .body(
                        "productId", is(productId.toString()),
                        "amount", is(amount),
                        "priceBase", is(priceBase),
                        "priceDiscounted", is(priceDiscounted)
                );
        // @formatter:on
    }

    static Stream<Arguments> shouldCalculateDiscountAndRespondWithCorrectJson() {
        return Stream.of(
                Arguments.of(UUID_ASUS, 1, new BigDecimal("3799.00"), new BigDecimal("3419.10")),
                Arguments.of(UUID_HUAWEI, 5, new BigDecimal("4999.00"), new BigDecimal("3749.25")),
                Arguments.of(UUID_IPHONE, 10, new BigDecimal("6499.00"), new BigDecimal("4224.35")),
                Arguments.of(UUID_SAMSUNG, 15, new BigDecimal("5199.00"), new BigDecimal("3379.35")),
                Arguments.of(UUID_XIAOMI, 30, new BigDecimal("3099.00"), new BigDecimal("1211.71"))
        );
    }

    @Test
    public void sholdRespondWithProductNotFoundError() {
        // given
        Integer amount = 10;
        UUID unknownProductId = UUID.fromString("82abf482-4789-44f7-bf92-17b6dd9c3f19");

        // @formatter:off
        given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                .param("productId", unknownProductId)
                .param("amount", amount)
        .when()
                .get(url("/calculate-price-discount"))
        .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON)
                .body(
                        "error", is("An error occurred during processing of your request"),
                        "cause", is("ProductNotFound"),
                        "exceptionMessage", is(String.format("Product does not exist [productId: %s]", unknownProductId))
                );
        // @formatter:on
    }

    @Test
    public void sholdRespondWithAmontConstraintViolationError() {
        // given
        Integer amount = -1;

        // @formatter:off
        given()
                .param("productId", UUID_ASUS)
                .param("amount", amount)
        .when()
                .get(url("/calculate-price-discount"))
        .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body(
                        "error", is("An error occurred during processing of your request"),
                        "cause", is("ConstraintViolationException"),
                        "exceptionMessage", is("calculatePriceDiscount.amount: must be greater than 0")
                );
        // @formatter:on
    }
}