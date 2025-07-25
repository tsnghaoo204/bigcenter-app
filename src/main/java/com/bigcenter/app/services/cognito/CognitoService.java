package com.bigcenter.app.services.cognito;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

public interface CognitoService {
    private CognitoIdentityProviderClient getClient() {
        return null;
    }

    void registerUser(String email, String password, String phone, String fullname);
    void confirmUser(String email, String code);
    String loginUser(String email, String password);
    void resendCode(String email);
}
