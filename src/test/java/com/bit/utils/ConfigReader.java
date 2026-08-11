package com.bit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads configuration from {@code config.properties} on the classpath.
 * Resolution order for each key: system property (-Dkey) &gt; environment
 * variable (KEY_IN_UPPER_SNAKE) &gt; properties file.
 */
public final class ConfigReader {

        private static final Properties PROPERTIES = new Properties();

        // List of all configuration files
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
                    //  warning or throw an exception if the file is mandatory.
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

        // 1. Priority №1: System Property (-Dkey=value из CLI)
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.isBlank()) {
            return sysProp;
        }

        // 2. Priority #2: Environment variables from Jenkins/OS (e.g., api.thinkingTester -> API_THINKINGTESTER or API_THINKING_TESTER)
        String envKey = key.replace('.', '_').toUpperCase();
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        // 3. Priority #3: Search in loaded .properties files (local execution)
        String propValue = PROPERTIES.getProperty(key);
        if (propValue != null && !propValue.isBlank()) {
            return propValue;
        }
        return null;
    }



    // 3. (Optional) Look in System.getProperty (-Dkey=value)
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    }


