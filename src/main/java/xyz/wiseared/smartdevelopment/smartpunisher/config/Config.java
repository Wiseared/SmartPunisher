package xyz.wiseared.smartdevelopment.smartpunisher.config;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.wiseared.smartdevelopment.smartpunisher.SmartPunisher;

public class Config {

    public static void init() {
        FileConfiguration config = SmartPunisher.get().getConfig();
    }
}