package Clients;

import Models.Booking;
import Models.BookingResponse;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class BookingClient {

    public BookingResponse createBooking(Booking booking){
        return given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(BookingResponse.class);
    }

    public Booking getBookingById(int bookingId){
        return given()
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);
    }

    public Booking updateBooking(int bookingId, Booking booking, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .body(booking)
                .when()
                .put("/booking/{id}")
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);
    }
    public void deleteBooking(int bookingId, String token){
        given()
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}")
                .then()
                .statusCode(201);
    }
    public int getBookingStatusCode(int bookingId){
        return given()
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .extract()
                .statusCode();
    }
}
