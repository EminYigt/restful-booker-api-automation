package Tests;

import Base.BaseApiTest;
import Clients.AuthClient;
import Models.AuthRequest;
import Models.AuthResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest extends BaseApiTest {

    @Test
    public void createTokenTest() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("admin");
        authRequest.setPassword("password123");

        AuthClient authClient = new AuthClient();

        AuthResponse authResponse =
                authClient.createToken(authRequest);

        Assert.assertNotNull(authResponse.getToken());
        Assert.assertFalse(authResponse.getToken().isBlank());
    }
}