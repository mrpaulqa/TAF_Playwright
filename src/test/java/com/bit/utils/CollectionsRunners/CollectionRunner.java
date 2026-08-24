package com.bit.utils.CollectionsRunners;

public interface CollectionRunner {

    void run(String collectionPath, String envName, String token);

    default void run(String collectionPath) {
        run(collectionPath, null, null);
    }

    default void runWithEnv(String collectionPath, String envName) {
        run(collectionPath, envName, null);
    }

    default void runWithToken(String collectionPath, String token) {
        run(collectionPath, null, token);
    }
}
