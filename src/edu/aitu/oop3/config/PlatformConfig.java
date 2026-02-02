package edu.aitu.oop3.config;

public class PlatformConfig {
    private static PlatformConfig instance;
    private String platformName = "AITU Learning Platform";

    private PlatformConfig() {}

    public static synchronized PlatformConfig getInstance() {
        if (instance == null) {
            instance = new PlatformConfig();
        }
        return instance;
    }

    public String getPlatformName() { return platformName; }
}