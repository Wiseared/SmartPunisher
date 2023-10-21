package xyz.wiseared.smartdevelopment.smartpunisher.command.commands;

import lombok.AllArgsConstructor;
import me.vaperion.blade.annotation.argument.Sender;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import me.vaperion.blade.annotation.command.Permission;
import org.bukkit.command.CommandSender;
import xyz.wiseared.smartdevelopment.smartpunisher.SmartPunisher;
import xyz.wiseared.smartdevelopment.smartpunisher.config.Config;
import xyz.wiseared.smartdevelopment.smartpunisher.util.Message;

@AllArgsConstructor
public class PunishGUICommand extends Message {

    private SmartPunisher plugin;

    @Command("punishgui reload")
    @Description("PunishGI reload command")
    @Permission("punishgui.reload")
    public final void punishGUIReloadCommand(@Sender CommandSender sender) {
        plugin.saveConfig();
        plugin.reloadConfig();

        Config.init();

        sendMessage(sender, " ");
        sendMessage(sender, "&4&lPunishGUI &7» &aReloaded all configs");
        sendMessage(sender, " ");
    }
}