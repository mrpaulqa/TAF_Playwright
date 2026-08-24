package com.bit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

        private static final Properties PROPERTIES = new Properties();

        private static final String[] CONFIG_FILES = {
                "config.properties",
                "users.properties",
                ".env.properties",
        };

        static {
            for (String fileName : CONFIG_FILES) {
                loadPropertiesFile(fileName);
            }
        }

        private ConfigReader() {
        }

        private static void loadPropertiesFile(String fileName) {
            try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
                if (in != null) {
                    PROPERTIES.load(in);
                } else {
                    System.out.println("WARN: Property file not found: " + fileName);
                }
            } catch (IOException e) {
                throw new ExceptionInInitializerError("Failed to load properties file: " + fileName + " - " + e.getMessage());
            }
        }

    public static String get(String key) {
        if (key == null || key.isBlank()) {
            return null;
        }

        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.isBlank()) {
            return sysProp;
        }
        String envKey = key.replace('.', '_').toUpperCase();
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }
        String propValue = PROPERTIES.getProperty(key);
        if (propValue != null && !propValue.isBlank()) {
            return propValue;
        }
        return null;
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    }


