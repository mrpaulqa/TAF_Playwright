package com.bit.utils.CollectionsRunners;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BrunoRunner implements CollectionRunner{
    public  void run(String collectionFolderPath, String envName, String token) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        File allureDir = new File("build/allure-results");
        if (!allureDir.exists()) {
            allureDir.mkdirs();
        }

        File collectionDir = new File(collectionFolderPath);
        String collectionName = collectionDir.getName();
        String junitReportPath = new File("build/allure-results/TEST-bruno-" + collectionName + ".xml").getAbsolutePath();
        StringBuilder command = new StringBuilder();
        command.append("npx @usebruno/cli run .")
                .append(" -r")
                .append(" --reporter-junit \"").append(junitReportPath).append("\"");
        if (envName != null && !envName.isEmpty()) {
            command.append(" --env ").append(envName);
        }
        if (token != null && !token.isEmpty()) {
            command.append(" --env-var ThinkiingTesterToken=").append(token);
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(collectionDir);

        if (isWindows) {
            processBuilder.command("cmd.exe", "/c", command.toString());
        } else {
            processBuilder.command("sh", "-c", command.toString());
        }

        processBuilder.inheritIO();

        try {
            System.out.println("🚀 Запуск Bruno коллекции в папке: " + collectionDir.getAbsolutePath());
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            File junitFile = new File(junitReportPath);
            if (junitFile.exists()) {
                try (FileInputStream fis = new FileInputStream(junitFile)) {
                    Allure.addAttachment("Bruno Execution Report (" + collectionName + ")", "text/xml", fis, ".xml");
                }
            }

            if (exitCode != 0) {
                throw new RuntimeException("❌ Ошибка при выполнении Bruno коллекции! Exit code: " + exitCode);
            } else {
                System.out.println("✅ Коллекция Bruno " + collectionName + " успешно выполнена!");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка запуска Bruno CLI: " + e.getMessage(), e);
        }
    }
}
