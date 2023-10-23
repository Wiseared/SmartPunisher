package xyz.wiseared.smartdevelopment.smartpunisher;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.wiseared.smartdevelopment.smartpunisher.command.CommandManager;
import xyz.wiseared.smartdevelopment.smartpunisher.config.Config;
import xyz.wiseared.smartdevelopment.smartpunisher.util.CC;
import xyz.wiseared.smartdevelopment.smartpunisher.util.menu.MenuHandler;

import java.io.IOException;

@Getter
public class SmartPunisher extends JavaPlugin {

    public static SmartPunisher get() {
        return getPlugin(SmartPunisher.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        CC.console(CC.LINE);
        CC.console(" ");

        CC.console("&7- Enabling &4&lSmartPunisher");
        CC.console("&7- Made By &cWiseared");
        CC.console("&7- Version &c" + getDescription().getVersion());

        CC.console("&7[&4Config&7] &7loaded &cconfig.yml");
        Config.init();

        try {
            registerManagers();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        CC.console(" ");
        CC.console("&7[&4&lSmartPunisher&7] &fEnabled &4&lSmartPunisher");

        CC.console(CC.LINE);
    }

    @Override
    public void onDisable() {
        long time = System.currentTimeMillis();

        CC.console(CC.LINE);
        CC.console(" ");

        CC.console("&7- Disabling &4&lSmartPunisher");
        CC.console("&7- Made By &cWiseared");
        CC.console("&7- Version &c" + getDescription().getVersion());
        CC.console(" ");

        CC.console("&7[&4&lSmartPunisher&7] &7Has been disabled in &a" + (System.currentTimeMillis() - time) + "ms");
        CC.console(CC.LINE);
    }

    private void registerManagers() throws IOException, ClassNotFoundException {
        new CommandManager(this);
        new MenuHandler(this);
    }
}