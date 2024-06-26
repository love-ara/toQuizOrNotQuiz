package africa.semicolon.service.services;

import africa.semicolon.data.model.User;
import africa.semicolon.dto.request.*;
import africa.semicolon.dto.response.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
    UserLoginResponse login(UserLoginRequest userLoginRequest);
    UserLogoutResponse logout(UserLogoutRequest userLogoutRequest);
    User findUserByUsername(String username);
}
