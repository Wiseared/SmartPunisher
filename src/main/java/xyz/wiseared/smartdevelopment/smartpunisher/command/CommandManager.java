package xyz.wiseared.smartdevelopment.smartpunisher.command;

import me.vaperion.blade.Blade;
import me.vaperion.blade.bukkit.BladeBukkitPlatform;
import xyz.wiseared.smartdevelopment.smartpunisher.SmartPunisher;
import xyz.wiseared.smartdevelopment.smartpunisher.command.commands.PunishCommand;
import xyz.wiseared.smartdevelopment.smartpunisher.command.commands.PunishGUICommand;
import xyz.wiseared.smartdevelopment.smartpunisher.command.help.CustomHelpGenerator;
import xyz.wiseared.smartdevelopment.smartpunisher.util.CC;

public class CommandManager {

    public CommandManager(SmartPunisher plugin) {
        Blade.forPlatform(new BladeBukkitPlatform(plugin))
                .config(cfg -> {
                    cfg.setHelpGenerator(new CustomHelpGenerator());
                    cfg.setExecutionTimeWarningThreshold(1000);
                    cfg.setOverrideCommands(true);
                    cfg.setFallbackPrefix("smartpunisher");
                    cfg.setDefaultPermissionMessage(CC.translate("&cNo permission!"));
                })
                .build()
                .register(new PunishCommand(plugin))
                .register(new PunishGUICommand(plugin));
    }
}