package com.bit.utils.CollectionsRunners;

public class RunnerFactory {
    public static CollectionRunner getRunner(String type) {
        return switch (type.toLowerCase()) {
            case "postman" -> new NewmanRunner();
            case "bruno"   -> new BrunoRunner();
            default -> throw new IllegalArgumentException("Collection not found: " + type);
        };
    }
}
