package com.bit.utils.CollectionsRunners;

import java.io.File;

public class NewmanRunner implements  CollectionRunner{
    public void run(String collectionPath, String environmentPath, String token) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        String collectionName = new File(collectionPath).getName().replace(".json", "");
        String junitReportPath = "build/allure-resultsx/TEST-postman-" + collectionName + ".xml";

        StringBuilder command = new StringBuilder();
        command.append("npx newman run \"").append(collectionPath).append("\"")
                .append(" -r cli,junit")
                .append(" --reporter-junit-export \"").append(junitReportPath).append("\"");

        if (environmentPath != null && !environmentPath.isEmpty()) {
            command.append(" -e \"").append(environmentPath).append("\"");
        }
        if (token != null && !token.isEmpty()) {
            command.append(" --env-var \"authToken=").append(token).append("\"");
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isWindows) {
            processBuilder.command("cmd.exe", "/c", command.toString());
        } else {
            processBuilder.command("sh", "-c", command.toString());
        }

    }

    }

