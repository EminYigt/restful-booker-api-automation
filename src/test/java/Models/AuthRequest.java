package Models;

import Clients.AuthClient;
import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;


}
