package com.bigcenter.app.services.cognito;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bigcenter.app.entities.User;
import com.bigcenter.app.repositories.UserRepository;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.*;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.*;

@Service
public class CognitoServiceImpl implements CognitoService {

    @Value("${COGNITO_CLIENT_ID}")
    private String clientId;

    @Value("${AWS_REGION}")
    private String region;

    @Value("${COGNITO_USER_POOL_ID}")
    private String poolId;

    @Value("${AWS_ACCESS_KEY}")
    private String accessKey;

    @Value("${AWS_SECRET_KEY}")
    private String secretKey;

    private final UserRepository userRepository;

    public CognitoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private CognitoIdentityProviderClient getClient() {
        return CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        accessKey,
                                        secretKey
                                )
                        ))
                .build();
    }

    private static final Set<String> listGroups = Set.of("STUDENT", "TEACHER", "ADMIN");


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

    public Map<String, Object> loginUser(String email, String password) {
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
        List<String> role = jwt.getClaim("cognito:groups").asList(String.class);

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

        return Map.of(
            "accessToken", accessToken,
            "role", role
        );
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

    @Override
    public List<UserType> getUserTypes(String groupName) {
        ListUsersInGroupRequest request = ListUsersInGroupRequest.builder()
                .userPoolId(poolId)
                .groupName(groupName)
                .build();

        ListUsersInGroupResponse response = getClient().listUsersInGroup(request);
        return response.users();
    }

    @Override
    public void deleteUser(String email) {
        AdminDeleteUserRequest request = AdminDeleteUserRequest.builder()
                .userPoolId(poolId)
                .username(email)
                .build();

        getClient().adminDeleteUser(request);
    }

    @Override
    public void changeUserRole(String email, String newGroup) {
        AdminAddUserToGroupRequest groupRequest = AdminAddUserToGroupRequest.builder()
                .username(email)
                .groupName(newGroup)
                .userPoolId(poolId)
                .build();

        getClient().adminAddUserToGroup(groupRequest);

        AdminListGroupsForUserRequest listGroupsForUserRequest = AdminListGroupsForUserRequest.builder()
                .username(email)
                .userPoolId(poolId)
                .build();

        AdminListGroupsForUserResponse listGroupsForUserResponse = getClient().adminListGroupsForUser(listGroupsForUserRequest);

        for (GroupType type : listGroupsForUserResponse.groups()) {
            String groupName = type.groupName();
            if (listGroups.contains(groupName) && !groupName.equals(newGroup)) {
                AdminRemoveUserFromGroupRequest request = AdminRemoveUserFromGroupRequest.builder()
                        .groupName(groupName)
                        .username(email)
                        .userPoolId(poolId)
                        .build();

                getClient().adminRemoveUserFromGroup(request);
            }
        }

    }

}

