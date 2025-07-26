package com.bigcenter.app.services.cognito;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bigcenter.app.entities.User;
import com.bigcenter.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.*;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class CognitoServiceImpl implements CognitoService {

    @Value("${COGNITO_CLIENT_ID}")
    private String clientId;

    @Value("${AWS_REGION}")
    private String region;
    private final UserRepository userRepository;

    public CognitoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private CognitoIdentityProviderClient getClient() {
        return CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }


    public void registerUser(String email, String password, String phone, String fullname) {
        SignUpRequest request = SignUpRequest.builder()
                .clientId(clientId)
                .username(email)
                .password(password)
                .userAttributes(AttributeType.builder().name("email").value(email).build(),
                        AttributeType.builder().name("phone_number").value(phone).build(),
                        AttributeType.builder().name("name").value(fullname).build())
                .build();
        getClient().signUp(request);
    }

    public void confirmUser(String email, String code) {
        ConfirmSignUpRequest request = ConfirmSignUpRequest.builder()
                .clientId(clientId)
                .username(email)
                .confirmationCode(code)
                .build();
        getClient().confirmSignUp(request);
    }

    public String loginUser(String email, String password) {
        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", email);
        authParams.put("PASSWORD", password);

        InitiateAuthRequest authRequest = InitiateAuthRequest.builder()
                .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .clientId(clientId)
                .authParameters(authParams)
                .build();

        InitiateAuthResponse response = getClient().initiateAuth(authRequest);
        String idToken = response.authenticationResult().idToken();
        String accessToken = response.authenticationResult().accessToken();
        DecodedJWT jwt = JWT.decode(idToken);
        String sub = jwt.getClaim("sub").asString();
        String userEmail = jwt.getClaim("email").asString();
        String phoneNumber = jwt.getClaim("phone_number").asString();
        String name = jwt.getClaim("name").asString();

        if (!userRepository.existsByCognitoSub(sub)) {
            User user = new User();
            user.setCognitoSub(sub);
            user.setEmail(userEmail);
            user.setEnable(true);
            user.setFullName(name);
            user.setPhone(phoneNumber);
            userRepository.save(user);
        }

        System.out.println("User logged in with sub: " + sub + ", email: " + userEmail);

        return accessToken;
    }

    @Override
    public void resendCode(String email) {
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.create();

        ResendConfirmationCodeRequest request = ResendConfirmationCodeRequest.builder()
                .clientId(clientId)
                .username(email)
                .build();

        ResendConfirmationCodeResponse response = cognitoClient.resendConfirmationCode(request);

        System.out.println("Code resent to: " + response.codeDeliveryDetails().destination());
    }

    @Override
    public void logout(String token) {
        getClient().globalSignOut(GlobalSignOutRequest.builder()
                .accessToken(token)
                .build());
    }
}

