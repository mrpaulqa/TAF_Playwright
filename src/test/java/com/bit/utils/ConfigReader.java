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

        // Список всех файлов конфигурации
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
                    // Можно сделать warning или выбросить исключение, если файл обязателен
                    System.out.println("WARN: Property file not found: " + fileName);
                }
            } catch (IOException e) {
                throw new ExceptionInInitializerError("Failed to load properties file: " + fileName + " - " + e.getMessage());
            }
        }

        public static String get(String key) {
            // 1. Приоритет: System Property (-Dkey=value)
            String sysProp = System.getProperty(key);
            if (sysProp != null && !sysProp.isBlank()) {
                return sysProp;
            }

            // 2. Приоритет: Переменные окружения (ENV)
            String envValue = System.getenv(key.replace('.', '_').toUpperCase());
            if (envValue != null && !envValue.isBlank()) {
                return envValue;
            }

            // 3. Приоритет: Из сmerged PROPERTIES (ищет по всем загруженным файлам)
            return PROPERTIES.getProperty(key);
        }
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    }


