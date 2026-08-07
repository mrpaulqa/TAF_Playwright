package com.bit.objects.Sauce;

import com.bit.utils.ConfigReader;

public class SauceUserFactory {

    public static SauceUserModel createSauceUser(String userRole) {
        if (userRole == null || userRole.isBlank()) {
            throw new IllegalArgumentException("User role cannot be null or empty");
        }

        String username = ConfigReader.get(userRole + ".username");
        String password = ConfigReader.get(userRole + ".password");

        if (username == null || password == null) {
            throw new IllegalArgumentException("Credentials for role '" + userRole + "' were not found in config");
        }

        return SauceUserModel.builder()
                .username(username)
                .password(password)
                .build();
    }
}
