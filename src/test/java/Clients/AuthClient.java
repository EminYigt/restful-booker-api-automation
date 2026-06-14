package Clients;
import Models.AuthRequest;
import io.restassured.http.ContentType;
import Models.AuthRequest;
import Models.AuthResponse;


import static io.restassured.RestAssured.given;
public class AuthClient {

    public AuthResponse createToken(AuthRequest request){
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class);
    }
}
