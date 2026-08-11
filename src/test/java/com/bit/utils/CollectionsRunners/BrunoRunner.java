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
        String junitReportPath = new File(allureDir, "TEST-bruno-" + collectionName + ".xml").getAbsolutePath();
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
        processBuilder.environment().put("LC_ALL", "C.UTF-8");
        processBuilder.environment().put("LANG", "C.UTF-8");
        processBuilder.directory(collectionDir);

        if (isWindows) {
            processBuilder.command("cmd.exe", "/c", command.toString());
        } else {
            processBuilder.command("sh", "-c", command.toString());
        }

        processBuilder.inheritIO();

        try {
            System.out.println("🚀 Run the Bruno collection in the folder: " + collectionDir.getAbsolutePath());
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            File junitFile = new File(junitReportPath);
            if (junitFile.exists()) {
                try (FileInputStream fis = new FileInputStream(junitFile)) {
                    Allure.addAttachment("Bruno Execution Report (" + collectionName + ")", "text/xml", fis, ".xml");
                }
            }

            if (exitCode != 0) {
                throw new RuntimeException("❌ Error executing Bruno collection! Exit code: " + exitCode);
            } else {
                System.out.println("✅ Bruno Collection " + collectionName + "successfully completed!");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error launching Bruno CLI: " + e.getMessage(), e);
        }
    }
}
