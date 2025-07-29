package com.bigcenter.app.services.cognito;

import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

import java.util.List;
import java.util.Map;

public interface CognitoService {
    private CognitoIdentityProviderClient getClient() {
        return null;
    }

    void registerUser(String email, String password, String phone, String fullname);
    void confirmUser(String email, String code);
    Map<String, Object> loginUser(String email, String password);
    void resendCode(String email);
    void logout(String token);
    List<UserType> getUserTypes(String groupName);
    void deleteUser(String email);
    public void changeUserRole(String email, String newGroup);
}
